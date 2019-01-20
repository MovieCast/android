/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.base.providers.response.models.general;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.moviecast.base.providers.response.models.movies.Movie;

public class Page {

    @SerializedName("page")
    private int pageNumber;
    @SerializedName("totalResults")
    private int totalResults;
    @SerializedName("totalPages")
    private int totalPages;
    @SerializedName("results")
    private List<Movie> movies = new ArrayList<>();

    public int getPageNumber() {
        return pageNumber;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
