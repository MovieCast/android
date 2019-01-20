/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.base.providers.response;

import io.moviecast.base.models.Media;
import io.moviecast.base.models.Show.Season;
import io.moviecast.base.providers.response.models.DetailResponse;
import io.moviecast.base.providers.response.models.general.Torrent;
import io.moviecast.base.providers.response.models.shows.Episode;
import io.moviecast.base.providers.response.models.shows.Show;

public class ShowDetailResponse extends Show implements DetailResponse {
    @Override
    public Media getFormattedItem() {
        io.moviecast.base.models.Show show = new io.moviecast.base.models.Show();

        // ResponseItem
        show.setId(getId());
        show.setTitle(getTitle());
        show.setYear(getYear());
        show.setRating(getRating().getPercentage() / 10);
        show.setGenres(getGenres());

        if(getImages() != null) {
            show.setPosterImageUrl(getImages().getPosterImage());
            show.setBackgroundImageUrl(getImages().getBackgroundImage());
        }

        show.setTvdbId(getTvdbId());
        show.setSynopsis(getSynopsis());
        show.setDuration(getDuration()); // TODO: Check what runtime is here...
        show.setCountry(getCountry());
        show.setNetwork(getNetwork());

        // TODO: Convert to a LocalDateTime
        show.setAirDay(getAirDay());
        show.setAirTime(getAirTime());

        show.setStatus(getStatus());
        show.setNumSeasons(getNumSeasons());

        for(Episode episodeItem : getEpisodes()) {
            Season.Episode episode = new Season.Episode();
            episode.setTvdbId(episodeItem.getTvdbId());
            episode.setSeason(episodeItem.getSeason());
            episode.setEpisode(episodeItem.getEpisode());
            episode.setTitle(episodeItem.getTitle());
            episode.setOverview(episodeItem.getOverview());

            // Yay torrents
            if(episodeItem.getTorrents() != null) {
                for(Torrent torrent : episodeItem.getTorrents()) {
                    episode.getTorrents().add(new io.moviecast.base.models.Torrent(torrent.getQuality(), torrent.getHash(),
                            torrent.getSeeds(), torrent.getPeers(), torrent.getSize()));
                }
            }

            show.addEpisode(episode);
        }
        return show;
    }
}
