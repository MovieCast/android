/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.moviecast.R;
import io.moviecast.base.models.Show;

public class ShowDetailFragment extends Fragment {
    private static final String ARG_SHOW = "ARG_SHOW";

    private Show show;

    public static ShowDetailFragment newInstance(Show show) {
        ShowDetailFragment fragment = new ShowDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SHOW, show);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            show = (Show) getArguments().getSerializable(ARG_SHOW);
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_detail, container, false);

        TextView title = view.findViewById(R.id.title);
        RatingBar ratingBar = view.findViewById(R.id.rating);
        TextView meta = view.findViewById(R.id.meta);
        TextView synopsis = view.findViewById(R.id.synopsis);
        LinearLayout season = view.findViewById(R.id.season);
        Button button = view.findViewById(R.id.episode);
        TabHost tabHost = view.findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("Deatails");
        spec.setContent(R.id.details);
        spec.setIndicator("Details");
        tabHost.addTab(spec);

//        for(int i = 0; i < show.getNumSeasons(); i++){
//            spec = tabHost.newTabSpec("Season " + (i + 1));
//            spec.setContent(R.id.season);
//            spec.setIndicator("Season " + (i + 1));
//            for(Show.Episode e : show.getEpisodes()){
//                if(e.getSeason() == i);
//                button =view.findViewById(R.id.episode);
//                button.setText("" + e.getEpisode());
//            }
//
//            tabHost.addTab(spec);
//        }
        for(Map.Entry<Integer, Show.Season> entry : show.getSeasons().entrySet()) {
            spec = tabHost.newTabSpec("Season " + entry.getKey());
            spec.setContent(R.id.season);
            spec.setIndicator("Season " + entry.getKey());
            tabHost.addTab(spec);
        }

        title.setText(show.getTitle()); List<String> metaList = new ArrayList<>();
        metaList.add(show.getYear());
        metaList.add(show.getStatus());
        metaList.add(String.join(", ", show.getGenres()));

        meta.setText(String.join(" • ", metaList));
        ratingBar.setProgress((int) show.getRating());
        synopsis.setText(show.getSynopsis());
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
