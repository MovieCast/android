/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.base.providers.response.models;

import io.moviecast.base.models.Media;

public interface DetailResponse {
    Media getFormattedItem();
}
