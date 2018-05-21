/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

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
    private long released;
    @JsonProperty("trailer")
    private String trailer;
    @JsonProperty("certification")
    private String certification;
    @JsonProperty("torrents")
    private ArrayList<Torrent> torrents;
    @JsonProperty("language")
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
