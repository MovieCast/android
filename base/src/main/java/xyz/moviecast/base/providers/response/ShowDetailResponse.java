package xyz.moviecast.base.providers.response;

import xyz.moviecast.base.models.Media;
import xyz.moviecast.base.providers.response.models.DetailResponse;
import xyz.moviecast.base.providers.response.models.general.Torrent;
import xyz.moviecast.base.providers.response.models.shows.Episode;
import xyz.moviecast.base.providers.response.models.shows.Show;

public class ShowDetailResponse extends Show implements DetailResponse {
    @Override
    public Media getFormattedItem() {
        xyz.moviecast.base.models.Show show = new xyz.moviecast.base.models.Show();

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
            xyz.moviecast.base.models.Show.Episode episode = new xyz.moviecast.base.models.Show.Episode();
            episode.setTvdbId(episodeItem.getTvdbId());
            episode.setSeason(episodeItem.getSeason());
            episode.setEpisode(episodeItem.getEpisode());
            episode.setTitle(episodeItem.getTitle());
            episode.setOverview(episodeItem.getOverview());

            // Yay torrents
            if(episodeItem.getTorrents() != null) {
                for(Torrent torrent : episodeItem.getTorrents()) {
                    episode.getTorrents().add(new xyz.moviecast.base.models.Torrent(torrent.getQuality(), torrent.getHash(),
                            torrent.getSeeds(), torrent.getPeers(), torrent.getSize()));
                }
            }

            show.getEpisodes().add(episode);
        }
        return show;
    }
}
