package xyz.moviecast.base.providers.response;

import java.util.ArrayList;
import java.util.List;

import xyz.moviecast.base.models.Media;
import xyz.moviecast.base.providers.response.models.ListResponse;
import xyz.moviecast.base.providers.response.models.general.Torrent;
import xyz.moviecast.base.providers.response.models.movies.Movie;
import xyz.moviecast.base.providers.response.models.shows.Show;

public class ShowListResponse extends ListResponse<Show> {
    @Override
    public List<Media> getFormattedResult() {
        List<Media> items = new ArrayList<>();
        for(Show item : result) {
            xyz.moviecast.base.models.Show movie = new xyz.moviecast.base.models.Show();

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

            items.add(movie);
        }

        return items;
    }
}
