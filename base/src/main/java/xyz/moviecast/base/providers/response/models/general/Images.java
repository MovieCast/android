/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.base.providers.response.models.general;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Images {

    @JsonProperty("background")
    private String backgroundImage;
    @JsonProperty("poster")
    private String posterImage;

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public String getPosterImage() {
        return posterImage;
    }
}
