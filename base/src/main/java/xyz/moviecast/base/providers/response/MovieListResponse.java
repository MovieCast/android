package xyz.moviecast.base.providers.response.models;

import java.util.ArrayList;
import java.util.List;

import xyz.moviecast.base.models.Media;
import xyz.moviecast.base.providers.response.models.movies.Movie;
import xyz.moviecast.base.providers.response.models.movies.Torrent;

public class MovieListResponse extends ListResponse<Movie> {
    @Override
    public List<Media> getFormattedResult() {
        List<Media> items = new ArrayList<>();
        for(Movie item : result) {
            xyz.moviecast.base.models.Movie movie = new xyz.moviecast.base.models.Movie();

            movie.setId(item.getId());
            movie.setTitle(item.getTitle());
            movie.setYear(item.getYear());

            movie.setSynopsis(item.getSynopsis());
            movie.setDuration(item.getDuration());
            movie.setCountry(item.getCountry());
            movie.setReleased(item.getReleased());
            movie.setTrailerUrl(item.getTrailer());
            movie.setCertification(item.getCertification());

            movie.setRating(item.getRating().getPercentage() / 10);
            movie.setGenres(item.getGenres());

            if(item.getTorrents() != null) {
                for(Torrent torrent : item.getTorrents()) {
                    movie.getTorrents().add(new xyz.moviecast.base.models.Torrent(torrent.getQuality(), torrent.getHash(),
                            torrent.getSeeds(), torrent.getPeers(), torrent.getSize()));
                }
            }

            if(item.getImages() != null) {
                movie.setPosterImageUrl(item.getImages().getPosterImage());
                movie.setBackgroundImageUrl(item.getImages().getBackgroundImage());
            }

            items.add(movie);
        }

        return items;
    }
}
