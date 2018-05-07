package xyz.moviecast.base.providers.response;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import xyz.moviecast.base.models.Media;
import xyz.moviecast.base.providers.response.models.ListResponse;
import xyz.moviecast.base.providers.response.models.movies.Movie;
import xyz.moviecast.base.providers.response.models.general.Torrent;

public class MovieListResponse extends ListResponse<Movie> {
    @Override
    public List<Media> getFormattedResult() {
        List<Media> items = new ArrayList<>();
        for(Movie item : result) {
            xyz.moviecast.base.models.Movie movie = new xyz.moviecast.base.models.Movie();

            // ResponseItem
            movie.setId(item.getId());
            movie.setTitle(item.getTitle());
            movie.setYear(item.getYear());
            movie.setRating(item.getRating().getPercentage() / 10);
            movie.setGenres(item.getGenres());

            if(item.getImages() != null) {
                movie.setPosterImageUrl(item.getImages().getPosterImage());
                movie.setBackgroundImageUrl(item.getImages().getBackgroundImage());
            }
            
            movie.setReleased(item.getReleased());

            items.add(movie);
        }

        return items;
    }
}
