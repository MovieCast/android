package xyz.moviecast.base.entities;

import java.util.ArrayList;

public class Movie {

    private String id;
    private String title;
    private String year;
    private String slug;
    private String synopsis;
    private int duration;
    private String country;
    private int released;
    private String trailer;
    private String certification;
    private ArrayList<Torrent> torrents = new ArrayList<>();
    private int votes;
    private int watching;
    private int percentage;
    private String backgroundImage;
    private String posterImage;
    private ArrayList<String> genres = new ArrayList<>();

    public Movie(String id, String title, String year, int released, int votes, int watching, int percentage, String backgroundImage, String posterImage) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.released = released;
        this.votes = votes;
        this.watching = watching;
        this.percentage = percentage;
        this.backgroundImage = backgroundImage;
        this.posterImage = posterImage;
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

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public ArrayList<Torrent> getTorrents() {
        return torrents;
    }

    public void setTorrents(ArrayList<Torrent> torrents) {
        this.torrents = torrents;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getWatching() {
        return watching;
    }

    public void setWatching(int watching) {
        this.watching = watching;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return String.format("id:%s, title:%s, year:%s, released:%s, votes:%s, watching:%s, percentage:%s, backgroundImage:%s, posterImage:%s",
                id, title, year, released, votes, watching, percentage, backgroundImage, posterImage);
    }
}
