/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.base.providers.response.models.shows;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.moviecast.base.providers.response.models.ResponseItem;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Show extends ResponseItem {
    @SerializedName("tvdb_id")
    private String tvdbId;
    @SerializedName("synopsis")
    private String synopsis;
    @SerializedName("runtime")
    private int duration;
    @SerializedName("country")
    private String country;
    @SerializedName("network")
    private String network;
    @SerializedName("air_day")
    private String airDay;
    @SerializedName("air_time")
    private String airTime;
    @SerializedName("status")
    private String status;
    @SerializedName("num_seasons")
    private int numSeasons;
    @SerializedName("episodes")
    private List<Episode> episodes;

    public Show() {

    }

    public String getTvdbId() {
        return tvdbId;
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

    public String getNetwork() {
        return network;
    }

    public String getAirDay() {
        return airDay;
    }

    public String getAirTime() {
        return airTime;
    }

    public String getStatus() {
        return status;
    }

    public int getNumSeasons() {
        return numSeasons;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }
}
