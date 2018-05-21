/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.base.providers.response.models.general;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rating {

    @JsonProperty("votes")
    private int votes;
    @JsonProperty("watching")
    private int watching;
    @JsonProperty("percentage")
    private int percentage;

    public int getVotes() {
        return votes;
    }

    public int getWatching() {
        return watching;
    }

    public int getPercentage() {
        return percentage;
    }
}
