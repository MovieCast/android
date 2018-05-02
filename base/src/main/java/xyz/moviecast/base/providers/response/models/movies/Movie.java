package xyz.moviecast.base.providers.response.models.movies;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import xyz.moviecast.base.providers.response.models.ResponseItem;
import xyz.moviecast.base.providers.response.models.general.Torrent;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie extends ResponseItem {

    @JsonProperty("slug")
    private String slug;
    @JsonProperty("synopsis")
    private String synopsis;
    @JsonProperty("runtime")
    private int duration;
    @JsonProperty("country")
    private String country;
    @JsonProperty("released")
    private int released;
    @JsonProperty("trailer")
    private String trailer;
    @JsonProperty("certification")
    private String certification;
    @JsonProperty("torrents")
    private ArrayList<Torrent> torrents;
    @JsonProperty("language")
    private String language;

    public Movie() {
        Log.d("Movie", "Using Movie Response Model");
    }

    public xyz.moviecast.base.models.Movie toApplicationMovie(){

        List<xyz.moviecast.base.models.Torrent> torrents = new ArrayList<>();
        if(this.torrents != null) {
            for (int i = 0; i < this.torrents.size(); i++) {
                Torrent torrent = this.torrents.get(i);
                torrents.add(new xyz.moviecast.base.models.Torrent(torrent.getQuality(), torrent.getHash(),
                        torrent.getSeeds(), torrent.getPeers(), torrent.getSize()));
            }
        }

        //xyz.moviecast.base.models.Rating rating = new xyz.moviecast.base.models.Rating(
        //        this.rating.getVotes(), this.rating.getWatching(), this.rating.getPercentage());

        //Images images = this.images != null ? this.images : new Images();

        return new xyz.moviecast.base.models.Movie(getId(), getTitle(), null, synopsis, duration,
               country, released, trailer, certification, torrents, null, null,
               null, null);
    }

    public String getSlug() {
        return slug;
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

    public int getReleased() {
        return released;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getCertification() {
        return certification;
    }

    public ArrayList<Torrent> getTorrents() {
        return torrents;
    }

    public String getLanguage() {
        return language;
    }
}
