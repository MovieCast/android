package xyz.moviecast.fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xyz.moviecast.R;
import xyz.moviecast.base.models.Show;

/**
 * Created by Bou's Laptop on 07/05/2018.
 */

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

        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        TextView title = view.findViewById(R.id.title);
        RatingBar ratingBar = view.findViewById(R.id.rating);
        TextView meta = view.findViewById(R.id.meta);
        TextView synopsis = view.findViewById(R.id.synopsis);

        title.setText(show.getTitle());
        ratingBar.setProgress((int) show.getRating());
        synopsis.setText(show.getSynopsis());

        List<String> metaList = new ArrayList<>();
        metaList.add(show.getYear());
        metaList.add(show.getStatus());
        metaList.add(String.join(", ", show.getGenres()));

        meta.setText(String.join(" • ", metaList));

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
