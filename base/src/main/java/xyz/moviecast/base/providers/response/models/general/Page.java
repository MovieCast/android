package xyz.moviecast.base.providers.response.models.general;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import xyz.moviecast.base.providers.response.models.movies.Movie;

public class Page {

    @JsonProperty("page")
    private int pageNumber;
    @JsonProperty("totalResults")
    private int totalResults;
    @JsonProperty("totalPages")
    private int totalPages;
    @JsonProperty("results")
    private List<Movie> movies = new ArrayList<>();

    public int getPageNumber() {
        return pageNumber;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
