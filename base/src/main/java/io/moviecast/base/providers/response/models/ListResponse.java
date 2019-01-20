/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.base.providers.response.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.moviecast.base.models.Media;

public abstract class ListResponse<T extends ResponseItem> {
    @SerializedName("page")
    protected int pageNumber;

    @SerializedName("totalResults")
    protected int totalResults;

    @SerializedName("totalPages")
    protected int totalPages;

    @SerializedName("results")
    protected List<T> result = new ArrayList<>();

    public int getPageNumber() {
        return pageNumber;
    }

    public List<T> getResult() {
        return result;
    }

    public abstract List<Media> getFormattedResult();

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
