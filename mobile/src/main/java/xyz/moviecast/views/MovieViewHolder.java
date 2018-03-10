package xyz.moviecast.views;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import xyz.moviecast.base.helpers.MovieHelper;
import xyz.moviecast.base.models.Movie;
import xyz.moviecast.base.helpers.MovieHelper.MovieHelperResult;

public class MovieViewHolder extends RecyclerView.ViewHolder{

    private MovieView movieView;
    private MovieHelper movieHelper;

    private String type;
    private int position;

    public MovieViewHolder(View itemView, MovieHelper movieHelper) {
        super(itemView);
        this.movieView = (MovieView) itemView;
        this.movieHelper = movieHelper;
    }

    public void updateMovie(String type, int position){
        this.type = type;
        this.position = position;
    }

    public void setMovie(Movie movie){
        movieView.setMovie(movie);
    }
}
