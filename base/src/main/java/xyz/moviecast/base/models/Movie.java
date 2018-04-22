package xyz.moviecast.base.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private String id;
    private String title;
    private String year;
    private String slug;
    private String synopsis;
    private int duration;
    private String country;
    private int released;
    private String trailerUrl;
    private String certification;
    private List<Torrent> torrents;
    private Rating rating;
    private String posterImageUrl;
    private String backgroundImageUrl;
    private List<String> genres = new ArrayList<>();

    public Movie(String id, String title, String year, String slug, String synopsis, int duration,
                 String country, int released, String trailerUrl, String certification,
                 List<Torrent> torrents, Rating rating, String posterImageUrl,
                 String backgroundImageUrl, List<String> genres) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.slug = slug;
        this.synopsis = synopsis;
        this.duration = duration;
        this.country = country;
        this.released = released;
        this.trailerUrl = trailerUrl;
        this.certification = certification;
        this.torrents = torrents;
        this.rating = rating;
        this.posterImageUrl = posterImageUrl;
        this.backgroundImageUrl = backgroundImageUrl;
        this.genres = genres;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getReleased() {
        return released;
    }

    public void setReleased(int released) {
        this.released = released;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public List<Torrent> getTorrents() {
        return torrents;
    }

    public void setTorrents(List<Torrent> torrents) {
        this.torrents = torrents;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getPosterImageUrl() {
        return posterImageUrl;
    }

    public void setPosterImageUrl(String posterImageUrl) {
        this.posterImageUrl = posterImageUrl;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public void setBackgroundImageUrl(String backgroundImageUrl) {
        this.backgroundImageUrl = backgroundImageUrl;
    }
}
