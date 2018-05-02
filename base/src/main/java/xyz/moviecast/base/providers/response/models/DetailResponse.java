package xyz.moviecast.base.providers.response.models;

import xyz.moviecast.base.models.Media;

public interface DetailResponse<T extends Media> {
    Media getFormattedItem(T existingMedia);
}
