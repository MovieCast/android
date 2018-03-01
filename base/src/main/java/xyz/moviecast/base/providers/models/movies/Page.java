package xyz.moviecast.base.providers.models.movies;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

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
