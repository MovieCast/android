package xyz.moviecast.base.providers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import xyz.moviecast.base.R;
import xyz.moviecast.base.models.Media;
import xyz.moviecast.base.providers.models.movies.Movie;
import xyz.moviecast.base.providers.models.general.Page;

public class MovieProvider extends MediaProvider {

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
    List<Media> formatList(String response) {
        ArrayList<Media> formattedItems = new ArrayList<>();

        try {
            Page page = mapper.readValue(response, Page.class);
            for(Movie movie : page.getMovies()) {
                formattedItems.add(movie.toApplicationMovie());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return formattedItems;
    }
}
