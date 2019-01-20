/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.base.models;

import android.os.Parcel;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
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
    private long released;
    private String trailerUrl;
    private String certification;
    private List<Torrent> torrents = new ArrayList<>();

    public Movie() {

    }

    private Movie(Parcel in) {
        super(in);
        synopsis = in.readString();
        duration = in.readInt();
        country = in.readString();
        released = in.readLong();
        trailerUrl = in.readString();
        certification = in.readString();
        torrents = in.createTypedArrayList(Torrent.CREATOR);
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

    public long getReleased() {
        return released;
    }

    public void setReleased(long released) {
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(synopsis);
        dest.writeInt(duration);
        dest.writeString(country);
        dest.writeLong(released);
        dest.writeString(trailerUrl);
        dest.writeString(certification);
        dest.writeTypedList(torrents);
    }

    @Override
    public ProviderManager.ProviderType getProviderType() {
        return ProviderManager.ProviderType.MOVIES;
    }
}
