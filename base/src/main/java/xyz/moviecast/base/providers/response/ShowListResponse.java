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
            xyz.moviecast.base.models.Show show = new xyz.moviecast.base.models.Show();

            // ResponseItem
            show.setId(item.getId());
            show.setTitle(item.getTitle());
            show.setYear(item.getYear());
            show.setRating(item.getRating().getPercentage() / 10);
            show.setGenres(item.getGenres());

            if(item.getImages() != null) {
                show.setPosterImageUrl(item.getImages().getPosterImage());
                show.setBackgroundImageUrl(item.getImages().getBackgroundImage());
            }

            show.setTvdbId(item.getTvdbId());
            show.setNumSeasons(item.getNumSeasons());

            items.add(show);
        }

        return items;
    }
}
