package xyz.moviecast.base.helpers;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import xyz.moviecast.base.models.Movie;
import xyz.moviecast.base.models.Torrent;
import xyz.moviecast.base.providers.MovieProvider;
import xyz.moviecast.base.providers.models.movies.Page;
import xyz.moviecast.base.providers.models.movies.Torrents;

public class MovieHelper {

    public static final int INTEGER_NO_RESULT = -1;

    private static MovieHelper instance;
    private MovieProvider provider;

    public MovieHelper getInstance(Context context){
        if(instance == null)
            instance = new MovieHelper(context);
        return instance;
    }

    public MovieHelper(Context context){
        provider = new MovieProvider(context);
    }

    public MovieHelperResult getMovie(String sorting, int position){
        try {
            Page page = provider.providePage(position / 50);
            xyz.moviecast.base.providers.models.movies.Movie movieModel = page.getMovies().get(position % 50);
            return null;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public MovieHelperResult getMovieListSize(){
        try{
            return new MovieHelperResult(provider.getTotalAmountOfMedia(), null);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public class MovieHelperResult{
        private int intRestult = INTEGER_NO_RESULT;
        private Movie movieResult = null;

        public MovieHelperResult(int intRestult, Movie movieResult){
            if(intRestult == INTEGER_NO_RESULT && movieResult != null)
                this.movieResult = movieResult;

            if(intRestult != INTEGER_NO_RESULT && movieResult == null){
                this.intRestult = intRestult;
            }
        }

        public int getIntRestult() {
            return intRestult;
        }

        public Movie getMovieResult() {
            return movieResult;
        }
    }
}
