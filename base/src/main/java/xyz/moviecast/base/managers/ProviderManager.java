package xyz.moviecast.base.managers;

import java.util.HashMap;
import java.util.Map;

import xyz.moviecast.base.models.Movie;
import xyz.moviecast.base.providers.MediaProvider;
import xyz.moviecast.base.providers.MovieProvider;

public class ProviderManager {

    private MovieProvider movieProvider;

    private MediaProvider currentProvider;

    //private Map<String, Movie> movieMap = new HashMap<>();

    ProviderManager(MovieProvider movieProvider) {
        this.movieProvider = movieProvider;
        this.currentProvider = movieProvider;
    }

    public MediaProvider getCurrentProvider() {
        return currentProvider;
    }
}
