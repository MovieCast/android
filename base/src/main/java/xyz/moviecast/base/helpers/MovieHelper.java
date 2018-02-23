package xyz.moviecast.base.helpers;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.moviecast.base.providers.ImageProvider;
import xyz.moviecast.base.providers.MovieProvider;
import xyz.moviecast.base.providers.models.movies.Movie;
import xyz.moviecast.base.providers.models.movies.Page;

public class MovieHelper {

    private static List<String> trending = new ArrayList<>();
    private static List<String> year = new ArrayList<>();
    private static List<String> alphabetical = new ArrayList<>();
    private static Map<String, Movie> jsonMoviesMap = new HashMap<>();
    private static Map<String, xyz.moviecast.base.models.Movie> applicationMovies = new HashMap<>();

    private MovieProvider movieProvider;
    private ImageProvider imageProvider;

    public MovieHelper(Context context) {
        movieProvider = new MovieProvider(context);
        imageProvider = new ImageProvider(context);
    }

    public void getMoviesOnPage(int page, final MovieHelperCallbacks callbacks){
        final int finalPage = page;
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
                        if(!jsonMoviesMap.containsKey(jsonMovies[i].getId())) {
                            jsonMoviesMap.put(jsonMovies[i].getId(), jsonMovies[i]);
                            applicationMovies.put(movies[i].getId(), movies[i]);
                        }
                    }
                    callbacks.onMoviesDone(movies);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getMoviePosterImage(final xyz.moviecast.base.models.Movie movie, final MovieHelperCallbacks callbacks){
        final Movie jsonMovie = jsonMoviesMap.get(movie.getId());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    byte[] imageData = imageProvider.provideImage(jsonMovie, ImageProvider.POSTER_IMAGE);
                    movie.setPosterImageData(imageData);
                    callbacks.onImageDone(movie, ImageProvider.POSTER_IMAGE);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public interface MovieHelperCallbacks {
        void onMoviesDone(xyz.moviecast.base.models.Movie[] movies);
        void onImageDone(xyz.moviecast.base.models.Movie movie, int type);
    }
}
