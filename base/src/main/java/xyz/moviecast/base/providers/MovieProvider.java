package xyz.moviecast.base.providers;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import xyz.moviecast.base.R;
import xyz.moviecast.base.models.Media;
import xyz.moviecast.base.models.Movie;
import xyz.moviecast.base.providers.response.MovieDetailResponse;
import xyz.moviecast.base.providers.response.MovieListResponse;

public class MovieProvider extends MediaProvider {

    MovieProvider(OkHttpClient client, ObjectMapper mapper) {
        super(client, mapper, "http://staging.content.moviecast.xyz", "movies", "detail");
    }

    @Override
    public List<Tab> getTabs() {
        List<Tab> tabs = new ArrayList<>();

        tabs.add(new Tab(R.id.movie_filter_trending, R.string.trending, Filters.Sort.TRENDING, Filters.Order.DESC));
        tabs.add(new Tab(R.id.movie_filter_popular, R.string.popular, Filters.Sort.POPULARITY, Filters.Order.DESC));
        tabs.add(new Tab(R.id.movie_filter_rating, R.string.rating, Filters.Sort.RATING, Filters.Order.DESC));
        tabs.add(new Tab(R.id.movie_filter_released, R.string.released, Filters.Sort.RELEASED, Filters.Order.DESC));
        tabs.add(new Tab(R.id.movie_filter_year, R.string.year, Filters.Sort.YEAR, Filters.Order.DESC));
        tabs.add(new Tab(R.id.movie_filter_alphabetic, R.string.alphabet, Filters.Sort.ALPHABET, Filters.Order.DESC));

        return tabs;
    }

    @Override
    Map<String, Media> formatList(String response) throws IOException {
        // REALLLY IMPORTINO TO USE A LINKEDHASHMAP!!!!!
        Map<String, Media> formattedItems = new LinkedHashMap<>();

        MovieListResponse page = mapper.readValue(response, MovieListResponse.class);
        for(Media item : page.getFormattedResult()) {
            formattedItems.put(item.getId(), item);
        }

        return formattedItems;
    }

    @Override
    Media formatDetail(String response) throws IOException {
        MovieDetailResponse detailResponse = mapper.readValue(response, MovieDetailResponse.class);
        return detailResponse.getFormattedItem();
    }
}
