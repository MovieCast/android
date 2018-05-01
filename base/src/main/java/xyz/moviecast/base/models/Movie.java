package xyz.moviecast.base.models;

import android.os.Parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import xyz.moviecast.base.managers.ProviderManager;

public class Movie extends Media implements Serializable {

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    private String synopsis;
    private int duration;
    private String country;
    private int released;
    private String trailerUrl;
    private String certification;
    private List<Torrent> torrents = new ArrayList<>();

    public Movie() {

    }

    public Movie(String id, String title, String year, String synopsis, int duration,
                 String country, int released, String trailerUrl, String certification,
                 List<Torrent> torrents, Rating rating, String posterImageUrl,
                 String backgroundImageUrl, List<String> genres) {
        super(id, title, year, rating, posterImageUrl, backgroundImageUrl, genres);
        this.synopsis = synopsis;
        this.duration = duration;
        this.country = country;
        this.released = released;
        this.trailerUrl = trailerUrl;
        this.certification = certification;
        this.torrents = torrents;
    }

    private Movie(Parcel in) {
        super(in);
        synopsis = in.readString();
        duration = in.readInt();
        country = in.readString();
        released = in.readInt();
        trailerUrl = in.readString();
        certification = in.readString();

        int torrentSize = in.readInt();
        for(int i = 0; i < torrentSize; i++) {
            Torrent torrent = in.readParcelable(Torrent.class.getClassLoader());
            torrents.add(torrent);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(synopsis);
        dest.writeInt(duration);
        dest.writeString(country);
        dest.writeInt(released);
        dest.writeString(trailerUrl);
        dest.writeString(certification);

        dest.writeInt(torrents.size());
        for(Torrent torrent : torrents) {
            dest.writeParcelable(torrent, flags);
        }
    }

    @Override
    public ProviderManager.ProviderType getProviderType() {
        return ProviderManager.ProviderType.MOVIES;
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
}
