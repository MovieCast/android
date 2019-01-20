/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import io.moviecast.MobileApplication;
import io.moviecast.R;
import io.moviecast.activities.MediaDetailActivity;
import io.moviecast.adapters.MediaGridAdapter;
import io.moviecast.base.app.BaseFragment;
import io.moviecast.base.managers.ProviderManager;
import io.moviecast.base.models.Media;
import io.moviecast.base.providers.MediaProvider;
import io.moviecast.base.utils.ThreadUtils;

public class MediaListFragment extends BaseFragment {

    public static final String ARG_MODE = "arg_mode";
    public static final String ARG_SORT = "arg_sort";
    public static final String ARG_ORDER = "arg_order";

    public enum Mode { NORMAL, SEARCH }

    private enum State {
        UNINITIALISED, LOADING, LOADED
    }

    @Inject
    ProviderManager providerManager;

    @Inject
    Context applicationContext;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private Mode mode = Mode.NORMAL;
    private State state = State.LOADING;
    private MediaProvider.Filters filters = new MediaProvider.Filters();
    private ArrayList<Media> items = new ArrayList<>();

    private Set<Media> itemResult = new LinkedHashSet<>();

    private GridLayoutManager layoutManager;
    private MediaGridAdapter mediaAdapter;

    private MediaProvider.MediaListCallback pageCallback = new MediaProvider.MediaListCallback() {
        @Override
        public void onSuccess(MediaProvider.Filters filters, Set<Media> result) {
            if(result.size() != 0) {
                itemResult.addAll(result);
                Log.d("MEDIA_LIST", "Successfully loaded " + result.size() + " new items, we have a total of " + itemResult.size() + " media items");
                ThreadUtils.runOnUiThread(() -> mediaAdapter.setResult(itemResult));
            }
            state = State.LOADED;
        }

        @Override
        public void onFailure(Exception e) {
            state = State.LOADED;
        }
    };

    public static MediaListFragment newInstance(Mode mode, MediaProvider.Filters.Sort sort, MediaProvider.Filters.Order order) {
        MediaListFragment fragment = new MediaListFragment();

        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_MODE, mode);
        arguments.putSerializable(ARG_SORT, sort);
        arguments.putSerializable(ARG_ORDER, order);

        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MobileApplication.getInstance()
                .getComponent()
                .inject(this);

        if(getArguments() != null) {
            mode = (Mode) getArguments().getSerializable(ARG_MODE);

            filters.setSort((MediaProvider.Filters.Sort) getArguments().getSerializable(ARG_SORT));
            filters.setOrder((MediaProvider.Filters.Order) getArguments().getSerializable(ARG_ORDER));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState, R.layout.fragment_media_catalog);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int visibleItems = layoutManager.getChildCount();
                int totalItems = layoutManager.getItemCount();
                int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                if((totalItems - visibleItems) <= firstVisibleItem + 6 && state == State.LOADED) {
                    state = State.LOADING;
                    filters.setPage(filters.getPage()+1);
                    providerManager.getCurrentProvider().providePage(filters, pageCallback);
                    Log.d("MEDIA_LIST", "Attempting to update item list, fetching page: " + filters.getPage());
                }
            }
        });

        mediaAdapter = new MediaGridAdapter(getActivity(), 2);
        mediaAdapter.setOnItemClickListener((v, media) -> {
            Log.d("MEDIA_LIST", "Clicked on media item " + media.getId() + " with title '" + media.getTitle() + "'");
//            Toast.makeText(getContext(), "Clicked on media item " + media.getId(), Toast.LENGTH_LONG).show();

            providerManager.getCurrentProvider().provideDetails(media.getId(), new MediaProvider.MediaDetailCallback() {
                @Override
                public void onSuccess(Media result) {
                    MediaDetailActivity.startActivity(getContext(), result);
                }

                @Override
                public void onFailure(Exception e) {
                    e.printStackTrace();
                    ThreadUtils.runOnUiThread(() -> Toast.makeText(getContext(), "Failed to load information", Toast.LENGTH_LONG).show());
                }
            });
        });
        recyclerView.setAdapter(mediaAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(mediaAdapter.getItemCount() == 0) {
            providerManager.getCurrentProvider().providePage(filters, pageCallback);
        }
    }
}
