/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.base.providers.response;

import android.util.Log;

import io.moviecast.base.models.Media;
import io.moviecast.base.providers.response.models.DetailResponse;
import io.moviecast.base.providers.response.models.general.Torrent;
import io.moviecast.base.providers.response.models.movies.Movie;

public class MovieDetailResponse extends Movie implements DetailResponse {
    @Override
    public Media getFormattedItem() {
        io.moviecast.base.models.Movie movie =new io.moviecast.base.models.Movie();

        // ResponseItem
        movie.setId(getId());
        movie.setTitle(getTitle());
        movie.setYear(getYear());
        movie.setRating(getRating().getPercentage() / 10);
        movie.setGenres(getGenres());

        if(getImages() != null) {
            movie.setPosterImageUrl(getImages().getPosterImage());
            movie.setBackgroundImageUrl(getImages().getBackgroundImage());
        }

        // Movie specific
        movie.setSynopsis(getSynopsis());
        movie.setDuration(getDuration());
        movie.setCountry(getCountry());
        movie.setReleased(getReleased());
        movie.setTrailerUrl(getTrailer());
        movie.setCertification(getCertification());

        if(getTorrents() != null) {
            for(Torrent torrent : getTorrents()) {
                movie.getTorrents().add(new io.moviecast.base.models.Torrent(torrent.getQuality(), torrent.getHash(),
                        torrent.getSeeds(), torrent.getPeers(), torrent.getSize()));
            }
        }

        Log.d("Movie", "Image: " + movie.getPosterImageUrl());

        return movie;
    }
}
