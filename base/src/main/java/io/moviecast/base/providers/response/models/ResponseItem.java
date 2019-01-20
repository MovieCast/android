/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.base.providers.response.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.moviecast.base.providers.response.models.general.Images;
import io.moviecast.base.providers.response.models.general.Rating;

public abstract class ResponseItem {

    @SerializedName("_id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("year")
    private String year;
    @SerializedName("rating")
    private Rating rating;
    @SerializedName("images")
    private Images images;
    @SerializedName("genres")
    private List<String> genres;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
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
