package xyz.moviecast.base.Helpers;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.moviecast.base.providers.MovieProvider;
import xyz.moviecast.base.providers.models.movies.Movie;
import xyz.moviecast.base.providers.models.movies.Page;

public class MovieHelper {

    private static List<String> trending = new ArrayList<>();
    private static List<String> year = new ArrayList<>();
    private static List<String> alphabetical = new ArrayList<>();
    private static Map<String, Movie> jsonMovies = new HashMap<>();
    private static Map<String, xyz.moviecast.base.models.Movie> applicationMovies = new HashMap<>();

    private MovieProvider movieProvider;

    public MovieHelper(Context context) {
        movieProvider = new MovieProvider(context);
    }

    public void getMoviesOnPage(int page, OnMoviesDoneListener callback){
        final int finalPage = page;
        final OnMoviesDoneListener finalCallback = callback;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Page page = movieProvider.providePage(finalPage);
                    List<Movie> movieList = page.getMovies();
                    Movie[] jsonMovies = new Movie[movieList.size()];
                    jsonMovies = movieList.toArray(jsonMovies);

                    xyz.moviecast.base.models.Movie[] movies = new xyz.moviecast.base.models.Movie[jsonMovies.length];
                    for(int i = 0; i < jsonMovies.length; i++){
                        movies[i] = jsonMovies[i].toApplicationMovie();
                    }
                    finalCallback.onMoviesDone(movies);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public interface OnMoviesDoneListener{
        void onMoviesDone(xyz.moviecast.base.models.Movie[] movies);
    }
}
