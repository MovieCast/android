/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.base.providers.response.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import xyz.moviecast.base.models.Media;

public abstract class ListResponse<T extends ResponseItem> {
    @JsonProperty("page")
    protected int pageNumber;

    @JsonProperty("totalResults")
    protected int totalResults;

    @JsonProperty("totalPages")
    protected int totalPages;

    @JsonProperty("results")
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
