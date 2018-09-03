/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.base.providers.response.models;

import xyz.moviecast.base.models.Media;

public interface DetailResponse {
    Media getFormattedItem();
}
