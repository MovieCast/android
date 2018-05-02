package xyz.moviecast.base.providers.response;

import xyz.moviecast.base.models.Media;
import xyz.moviecast.base.providers.response.models.DetailResponse;
import xyz.moviecast.base.providers.response.models.general.Torrent;
import xyz.moviecast.base.providers.response.models.movies.Movie;

public class MovieDetailResponse extends Movie implements DetailResponse<xyz.moviecast.base.models.Movie> {
    @Override
    public Media getFormattedItem(xyz.moviecast.base.models.Movie existingMovie) {
        // ResponseItem
        existingMovie.setId(getId());
        existingMovie.setTitle(getTitle());
        existingMovie.setYear(getYear());
        existingMovie.setRating(getRating().getPercentage() / 10);
        existingMovie.setGenres(getGenres());

        if(getImages() != null) {
            existingMovie.setPosterImageUrl(getImages().getPosterImage());
            existingMovie.setBackgroundImageUrl(getImages().getBackgroundImage());
        }

        // Movie specific
        existingMovie.setSynopsis(getSynopsis());
        existingMovie.setDuration(getDuration());
        existingMovie.setCountry(getCountry());
        existingMovie.setReleased(getReleased());
        existingMovie.setTrailerUrl(getTrailer());
        existingMovie.setCertification(getCertification());

        if(getTorrents() != null) {
            for(Torrent torrent : getTorrents()) {
                existingMovie.getTorrents().add(new xyz.moviecast.base.models.Torrent(torrent.getQuality(), torrent.getHash(),
                        torrent.getSeeds(), torrent.getPeers(), torrent.getSize()));
            }
        }

        return existingMovie;
    }
}
