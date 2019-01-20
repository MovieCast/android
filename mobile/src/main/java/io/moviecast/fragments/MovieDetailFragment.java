/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.moviecast.R;
import io.moviecast.base.app.BaseFragment;
import io.moviecast.base.models.Movie;

/**
 * A simple {@link Fragment} subclass.
 *
 * Use the {@link MovieDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailFragment extends BaseFragment {
    private static final String ARG_MOVIE = "ARG_MOVIE";

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rating)
    RatingBar ratingBar;
    @BindView(R.id.meta)
    TextView meta;
    @BindView(R.id.synopsis)
    TextView synopsis;

    // TODO: Rename and change types of parameters
    private Movie movie;

    public MovieDetailFragment() { }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param movie The movie to display.
     * @return A new instance of MovieDetailFragment.
     */
    public static MovieDetailFragment newInstance(Movie movie) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            movie = (Movie) getArguments().getSerializable(ARG_MOVIE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState, R.layout.fragment_movie_detail);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title.setText(movie.getTitle());
        ratingBar.setProgress((int) movie.getRating());
        synopsis.setText(movie.getSynopsis());

        List<String> metaList = new ArrayList<>();
        metaList.add(movie.getYear());
        metaList.add(movie.getDuration() + " min");
        metaList.add(TextUtils.join(", ", movie.getGenres()));

        meta.setText(TextUtils.join(" • ", metaList));
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
