/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.moviecast.R;
import xyz.moviecast.adapters.MediaGridAdapter;

public class MediaCatalogFragment extends Fragment{

    private static final String TAG = "MEDIA_CATALOG";
    public static final String KEY_TYPE = "TYPE";
    public static final String KEY_SORTING = "SORTING";

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media_catalog, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        //recyclerView.setAdapter(new RecyclerViewAdapter(type, sorting, getContext()));
        recyclerView.setAdapter(new MediaGridAdapter(getContext(), 2));

        return view;
    }
}
