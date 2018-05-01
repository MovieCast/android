package xyz.moviecast.base.providers.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import xyz.moviecast.base.models.Media;

public abstract class ListResponse<T extends ResponseItem> {
    @JsonProperty("page")
    private int pageNumber;

    @JsonProperty("totalResults")
    private int totalResults;

    @JsonProperty("totalPages")
    private int totalPages;

    @JsonProperty("results")
    private List<T> items = new ArrayList<>();

    public int getPageNumber() {
        return pageNumber;
    }

    public List<T> getResult() {
        return items;
    }

    public abstract List<Media> getFormattedResult();

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
