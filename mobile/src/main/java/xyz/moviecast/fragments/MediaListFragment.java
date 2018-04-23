package xyz.moviecast.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import xyz.moviecast.MobileApplication;
import xyz.moviecast.R;
import xyz.moviecast.adapters.MediaGridAdapter;
import xyz.moviecast.base.managers.ProviderManager;
import xyz.moviecast.base.models.Media;
import xyz.moviecast.base.models.Movie;
import xyz.moviecast.base.providers.MediaProvider;
import xyz.moviecast.base.utils.ThreadUtils;

public class MediaListFragment extends Fragment {

    public static final String ARG_MODE = "arg_mode";
    public static final String ARG_SORT = "arg_sort";
    public static final String ARG_ORDER = "arg_order";

    public enum Mode { NORMAL, SEARCH }

    @Inject
    ProviderManager providerManager;

    private Mode mode = Mode.NORMAL;
    private MediaProvider.Filters filters = new MediaProvider.Filters();
    private ArrayList<Media> items = new ArrayList<>();

    private RecyclerView recyclerView;

    private MediaGridAdapter mediaAdapter;

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

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // TODO: Update page here...
                Log.d("MEDIA_LIST", "Attempting to update item list");
            }
        });

        mediaAdapter = new MediaGridAdapter(getActivity(), new ArrayList<>(), 2);
        recyclerView.setAdapter(mediaAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(mediaAdapter.getItemCount() == 0) {
            providerManager.getCurrentProvider().providePage(filters, new MediaProvider.MediaCallback() {
                @Override
                public void onSuccess(MediaProvider.Filters filters, List<Media> newItems) {
                    items.addAll(newItems);
                    Log.d("MEDIA_LIST", "Successfully loaded " + newItems.size() + " new items, we have a total of " + items.size() + " media items");

                    ThreadUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mediaAdapter.setItems(items);
                            //mPreviousTotal = mTotalItemCount = mAdapter.getItemCount();
                        }
                    });
                }

                @Override
                public void onFailure(Exception e) {

                }
            });
        }
    }
}
