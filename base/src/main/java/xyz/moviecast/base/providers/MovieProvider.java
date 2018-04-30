package xyz.moviecast.base.providers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import xyz.moviecast.base.R;
import xyz.moviecast.base.models.Media;
import xyz.moviecast.base.providers.models.movies.Movie;
import xyz.moviecast.base.providers.models.general.Page;

public class MovieProvider extends MediaProvider {

    MovieProvider(OkHttpClient client, ObjectMapper mapper) {
        super(client, mapper, "http://staging.content.moviecast.xyz", "/movies/", "/detail/");
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
    public xyz.moviecast.base.models.Movie getMediaById(String id) {
        return (xyz.moviecast.base.models.Movie) super.getMediaById(id);
    }

    @Override
    Map<String, Media> formatList(String response) {
        // REALLLY IMPORTINO TO USE A LINKEDHASHMAP!!!!!
        Map<String, Media> formattedItems = new LinkedHashMap<>();

        try {
            Page page = mapper.readValue(response, Page.class);
            for(Movie movie : page.getMovies()) {
                formattedItems.put(movie.getId(), movie.toApplicationMovie());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return formattedItems;
    }
}
