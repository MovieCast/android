package xyz.moviecast.base.providers.response;

import xyz.moviecast.base.models.Media;
import xyz.moviecast.base.providers.response.models.DetailResponse;
import xyz.moviecast.base.providers.response.models.general.Torrent;
import xyz.moviecast.base.providers.response.models.movies.Movie;

public class MovieDetailResponse extends Movie implements DetailResponse {
    @Override
    public Media getFormattedItem() {
        xyz.moviecast.base.models.Movie movie =new xyz.moviecast.base.models.Movie();

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
                movie.getTorrents().add(new xyz.moviecast.base.models.Torrent(torrent.getQuality(), torrent.getHash(),
                        torrent.getSeeds(), torrent.getPeers(), torrent.getSize()));
            }
        }

        return movie;
    }
}
