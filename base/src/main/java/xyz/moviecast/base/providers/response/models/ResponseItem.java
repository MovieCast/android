package xyz.moviecast.base.providers.response.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import xyz.moviecast.base.providers.response.models.general.Images;
import xyz.moviecast.base.providers.response.models.general.Rating;

public abstract class ResponseItem {
    @JsonProperty("_id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("year")
    private String year;
    @JsonProperty("rating")
    private Rating rating;
    @JsonProperty("images")
    private Images images;
    @JsonProperty("genres")
    private List<String> genres;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public Rating getRating() {
        return rating;
    }

    public Images getImages() {
        return images;
    }

    public List<String> getGenres() {
        return genres;
    }
}
