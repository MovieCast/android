package xyz.moviecast.fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xyz.moviecast.R;
import xyz.moviecast.base.models.Movie;

/**
 * A simple {@link Fragment} subclass.
 *
 * Use the {@link MovieDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailFragment extends Fragment {
    private static final String ARG_MOVIE = "ARG_MOVIE";

    // TODO: Rename and change types of parameters
    private Movie movie;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

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

        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        TextView title = view.findViewById(R.id.title);
        RatingBar ratingBar = view.findViewById(R.id.rating);
        TextView meta = view.findViewById(R.id.meta);
        TextView synopsis = view.findViewById(R.id.synopsis);

        title.setText(movie.getTitle());
        ratingBar.setProgress((int) movie.getRating());
        synopsis.setText(movie.getSynopsis());

        List<String> metaList = new ArrayList<>();
        metaList.add(movie.getYear());
        metaList.add(movie.getDuration() + " min");
        metaList.add(TextUtils.join(", ", movie.getGenres()));

        meta.setText(TextUtils.join(" • ", metaList));

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
