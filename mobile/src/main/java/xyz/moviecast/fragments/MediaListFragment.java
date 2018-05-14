package xyz.moviecast.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.inject.Inject;

import xyz.moviecast.MobileApplication;
import xyz.moviecast.R;
import xyz.moviecast.activities.MediaDetailActivity;
import xyz.moviecast.adapters.MediaGridAdapter;
import xyz.moviecast.base.managers.ProviderManager;
import xyz.moviecast.base.models.Media;
import xyz.moviecast.base.providers.MediaProvider;
import xyz.moviecast.base.utils.ThreadUtils;

public class MediaListFragment extends Fragment {

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

    private Mode mode = Mode.NORMAL;
    private State state = State.LOADING;
    private MediaProvider.Filters filters = new MediaProvider.Filters();
    private ArrayList<Media> items = new ArrayList<>();

    private Set<Media> itemResult = new LinkedHashSet<>();

    private RecyclerView recyclerView;

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
        View view = inflater.inflate(R.layout.fragment_media_catalog, container, false);

        layoutManager = new GridLayoutManager(getActivity(), 2);

        recyclerView = view.findViewById(R.id.recyclerView);
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
            Toast.makeText(getContext(), "Clicked on media item " + media.getId(), Toast.LENGTH_LONG).show();

            providerManager.getCurrentProvider().provideDetails(media.getId(), new MediaProvider.MediaDetailCallback() {
                @Override
                public void onSuccess(Media result) {
                    Intent detailIntent = new Intent(applicationContext, MediaDetailActivity.class);
                    Log.d("MEDIA_DETAIL", "onSuccess: " + media);
                    detailIntent.putExtra(MediaDetailActivity.MEDIA_OBJECT, result);

                    startActivity(detailIntent);
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
