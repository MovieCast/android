/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.base.providers.response.models.general;

import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("background")
    private String backgroundImage;
    @SerializedName("poster")
    private String posterImage;

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public String getPosterImage() {
        return posterImage;
    }
}
