package xyz.moviecast.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import xyz.moviecast.R;
import xyz.moviecast.base.Helpers.MovieHelper;
import xyz.moviecast.base.models.Movie;
import xyz.moviecast.base.models.Rating;
import xyz.moviecast.base.models.Torrent;
import xyz.moviecast.views.MovieCatalogView;

public class MovieFragment extends Fragment implements MovieHelper.MovieHelperCallbacks{

    private MovieCatalogView movieCatalogView;
    private MovieHelper movieHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        movieCatalogView = view.findViewById(R.id.movieCatalogView);
        movieHelper = new MovieHelper(getContext());
        movieHelper.getMoviesOnPage(1, this);
        return view;
    }

    @Override
    public void onMoviesDone(Movie[] movies) {
        for(int i = 0; i < movies.length; i++){
            movieHelper.getMoviePosterImage(movies[i],this);
        }
    }

    @Override
    public void onImageDone(final Movie movie, int type) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                movieCatalogView.addMovie(movie);
            }
        });
    }
}
