package xyz.moviecast.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.moviecast.R;
import xyz.moviecast.adapters.RecyclerViewAdapter;
import xyz.moviecast.base.Constants;

public class MediaCatalogFragment extends Fragment{

    private static final String TAG = "MEDIA_CATALOG";
    public static final String KEY_TYPE = "TYPE";
    public static final String KEY_SORTING = "SORTING";

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        int type = args.getInt(KEY_TYPE, Constants.MOVIES);
        String sorting = args.getString(KEY_SORTING, Constants.TRENDING);

        View view = inflater.inflate(R.layout.fragment_media_catalog, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        recyclerView.setAdapter(new RecyclerViewAdapter(type, sorting));

        return view;
    }
}
