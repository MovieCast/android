package xyz.moviecast.base.providers.models.movies;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import xyz.moviecast.base.models.Torrent;

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
    private Torrents torrents;
    @JsonProperty("rating")
    private Rating rating;
    @JsonProperty("images")
    private Images images;
    @JsonProperty("genres")
    private List<String> genres = new ArrayList<>();

    public Movie() {

    }

    public xyz.moviecast.base.models.Movie toApplicationMovie(){
        List<Torrent> torrents = null;
        if(this.torrents != null) {
            torrents = new ArrayList<>();
            Map<String, Quality> qualities = this.torrents.getQualities();

            Iterator<String> stringIterator = qualities.keySet().iterator();
            while (stringIterator.hasNext()) {
                String key = stringIterator.next();
                Quality quality = qualities.get(key);
                torrents.add(new Torrent(key, quality.getHash(), quality.getSeeds(), quality.getPeers(), quality.getSize(), quality.getFileSize(), quality.getProvider()));
            }
        }

        return new xyz.moviecast.base.models.Movie(id, title, year, slug, synopsis, duration, country, released, trailer, certification,
                torrents, new xyz.moviecast.base.models.Rating(rating.getVotes(), rating.getWatching(), rating.getPercentage()), genres);
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

    public Torrents getTorrents() {
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
}
