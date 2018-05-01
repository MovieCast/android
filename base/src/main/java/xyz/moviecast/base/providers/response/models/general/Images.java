package xyz.moviecast.base.providers.models.general;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Images {

    @JsonProperty("background")
    private String backgroundImage;
    @JsonProperty("poster")
    private String posterImage;

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public String getPosterImage() {
        return posterImage;
    }
}
