package xyz.moviecast.base.providers.models;

import org.codehaus.jackson.annotate.JsonProperty;

//TODO: Remove this class
public class ResultsPage {

    @JsonProperty("totalResults")
    private int totalRestults;
    @JsonProperty("totalPages")
    private int totalPages;

    public ResultsPage(){}

    public int getTotalRestults() {
        return totalRestults;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
