package xyz.moviecast.base.providers.models.movies;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import xyz.moviecast.base.providers.models.general.Images;
import xyz.moviecast.base.providers.models.general.Rating;

public class Movie {

    @JsonProperty("_id")
    private String id;
    @JsonProperty("imdb_id")
    private String imdbId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("year")
    private String year;
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
    @JsonProperty("rating")
    private Rating rating;
    @JsonProperty("images")
    private Images images = new Images();
    @JsonProperty("genres")
    private List<String> genres = new ArrayList<>();
    @JsonProperty("language")
    private String language;

    public Movie() {

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

        xyz.moviecast.base.models.Rating rating = new xyz.moviecast.base.models.Rating(
                this.rating.getVotes(), this.rating.getWatching(), this.rating.getPercentage());

        Images images = this.images != null ? this.images : new Images();

        return new xyz.moviecast.base.models.Movie(id, title, year, synopsis, duration,
               country, released, trailer, certification, torrents, rating, images.getPosterImage(),
               images.getBackgroundImage(), genres);
    }

    public String getId() {
        return id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
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

    public Rating getRating() {
        return rating;
    }

    public Images getImages() {
        return images;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getLanguage() {
        return language;
    }
}
