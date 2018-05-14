package xyz.moviecast.base.providers.response.models.shows;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import xyz.moviecast.base.providers.response.models.ResponseItem;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Show extends ResponseItem {
    @JsonProperty("tvdb_id")
    private String tvdbId;
    @JsonProperty("synopsis")
    private String synopsis;
    @JsonProperty("runtime")
    private int duration;
    @JsonProperty("country")
    private String country;
    @JsonProperty("network")
    private String network;
    @JsonProperty("air_day")
    private String airDay;
    @JsonProperty("air_time")
    private String airTime;
    @JsonProperty("status")
    private String status;
    @JsonProperty("num_seasons")
    private int numSeasons;
    @JsonProperty("episodes")
    private List<Episode> episodes;

    public Show() {

    }

    public String getTvdbId() {
        return tvdbId;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public int getDuration() {
        return duration;
    }

    public String getCountry() {
        return country;
    }

    public String getNetwork() {
        return network;
    }

    public String getAirDay() {
        return airDay;
    }

    public String getAirTime() {
        return airTime;
    }

    public String getStatus() {
        return status;
    }

    public int getNumSeasons() {
        return numSeasons;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }
}
