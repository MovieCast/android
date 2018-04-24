package xyz.moviecast.base.models;

import java.util.List;

import xyz.moviecast.base.managers.ProviderManager;

public class Movie extends Media {

    private String slug;
    private String synopsis;
    private int duration;
    private String country;
    private int released;
    private String trailerUrl;
    private String certification;
    private List<Torrent> torrents;
    private List<String> genres;

    public Movie(String id, String title, String year, String slug, String synopsis, int duration,
                 String country, int released, String trailerUrl, String certification,
                 List<Torrent> torrents, Rating rating, String posterImageUrl,
                 String backgroundImageUrl, List<String> genres) {
        super(id, title, year, rating, posterImageUrl, backgroundImageUrl);
        this.slug = slug;
        this.synopsis = synopsis;
        this.duration = duration;
        this.country = country;
        this.released = released;
        this.trailerUrl = trailerUrl;
        this.certification = certification;
        this.torrents = torrents;
        this.genres = genres;
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

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @Override
    public ProviderManager.ProviderType getProviderType() {
        return ProviderManager.ProviderType.MOVIES;
    }
}
