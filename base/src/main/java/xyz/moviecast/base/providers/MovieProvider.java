package xyz.moviecast.base.providers;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import xyz.moviecast.base.R;
import xyz.moviecast.base.providers.models.movies.Movie;
import xyz.moviecast.base.providers.models.movies.Page;

public class MovieProvider extends MediaProvider<Movie> {

    public MovieProvider(OkHttpClient client, ObjectMapper mapper) {
        super(client, mapper, "http://staging.content.moviecast.xyz", "/movies/", "/detail/");
    }

    @Override
    public List<Tab> getTabs() {
        List<Tab> tabs = new ArrayList<>();

        tabs.add(new Tab(R.id.movie_filter_trending, "Trending", Filters.Sort.TRENDING, Filters.Order.DESC));
        tabs.add(new Tab(R.id.movie_filter_popular, "Popular", Filters.Sort.POPULARITY, Filters.Order.DESC));
        tabs.add(new Tab(R.id.movie_filter_rating, "Rating", Filters.Sort.RATING, Filters.Order.DESC));
        tabs.add(new Tab(R.id.movie_filter_released, "Released", Filters.Sort.RELEASED, Filters.Order.DESC));
        tabs.add(new Tab(R.id.movie_filter_year, "Year", Filters.Sort.YEAR, Filters.Order.DESC));
        tabs.add(new Tab(R.id.movie_filter_alphabetic, "A - Z", Filters.Sort.ALPHABET, Filters.Order.ASC));

        return tabs;
    }

    @Override
    List<Movie> formatList(String response) {
        try {
            Page page = mapper.readValue(response, Page.class);
            return page.getMovies();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // UHMMM??
        return null;
    }
}
