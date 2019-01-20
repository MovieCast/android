/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.base.providers.response.models.movies;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import io.moviecast.base.providers.response.models.ResponseItem;
import io.moviecast.base.providers.response.models.general.Torrent;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie extends ResponseItem {

    @SerializedName("slug")
    private String slug;
    @SerializedName("synopsis")
    private String synopsis;
    @SerializedName("runtime")
    private int duration;
    @SerializedName("country")
    private String country;
    @SerializedName("released")
    private long released;
    @SerializedName("trailer")
    private String trailer;
    @SerializedName("certification")
    private String certification;
    @SerializedName("torrents")
    private ArrayList<Torrent> torrents;
    @SerializedName("language")
    private String language;

    public Movie() {

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

    public long getReleased() {
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
