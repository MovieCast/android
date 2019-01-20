/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.base.providers.response.models.shows;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.moviecast.base.providers.response.models.general.Torrent;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Episode {
    @SerializedName("tvdb_id")
    private String tvdbId;
    @SerializedName("season")
    private int season;
    @SerializedName("episode")
    private int episode;
    @SerializedName("title")
    private String title;
    @SerializedName("overview")
    private String overview;
    @SerializedName("torrents")
    private List<Torrent> torrents;

    public Episode() {

    }

    public String getTvdbId() {
        return tvdbId;
    }

    public int getSeason() {
        return season;
    }

    public int getEpisode() {
        return episode;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public List<Torrent> getTorrents() {
        return torrents;
    }
}
