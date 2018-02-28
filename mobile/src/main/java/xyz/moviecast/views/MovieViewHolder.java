package xyz.moviecast.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import xyz.moviecast.base.models.Movie;

/**
 * Class description  here
 *
 * @author Tim
 */

public class MovieViewHolder extends RecyclerView.ViewHolder{

    private MovieView movieView;

    public MovieViewHolder(View itemView) {
        super(itemView);
        this.movieView = (MovieView) itemView;
    }

    public void setMovie(Movie movie){
        movieView.setMovie(movie);
    }

}
