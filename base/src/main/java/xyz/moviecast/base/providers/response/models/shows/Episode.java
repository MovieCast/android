package xyz.moviecast.base.providers.response.models.shows;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import xyz.moviecast.base.providers.response.models.general.Torrent;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Episode {
    @JsonProperty("tvdb_id")
    private String tvdbId;
    @JsonProperty("season")
    private int season;
    @JsonProperty("episode")
    private int episode;
    @JsonProperty("title")
    private String title;
    @JsonProperty("overview")
    private String overview;
    @JsonProperty("torrents")
    private List<Torrent> torrents;

    public Episode() {

    }

    public String getTvdbId() {
        return tvdbId;
    }

    public int getSeason() {
        return season;
    }

    public int getEpisode() {
        return episode;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public List<Torrent> getTorrents() {
        return torrents;
    }
}
