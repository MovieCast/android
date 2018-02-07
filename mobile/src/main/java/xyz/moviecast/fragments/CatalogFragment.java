package xyz.moviecast.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.moviecast.R;
import xyz.moviecast.base.Helpers.MovieHelper;
import xyz.moviecast.base.models.Movie;
import xyz.moviecast.views.MovieCatalogView;

public class CatalogFragment extends Fragment implements MovieHelper.MovieHelperCallbacks{

    public static final String TRENDING = "trending";
    public static final String YEAR = "year";
    public static final String ALPHABETICAL = "a-z";

    public static final int MOVIES = 0;
    public static final int SERIES = 1;
    public static final int ANIME = 2;

    private MovieCatalogView movieCatalogView;
    private MovieHelper movieHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //TODO: Add check for the type of catalog and sorting

        View view = inflater.inflate(R.layout.fragment_catalog_movie, container, false);
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
