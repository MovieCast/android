package xyz.moviecast.views;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import xyz.moviecast.activities.MainActivity;
import xyz.moviecast.base.helpers.MovieHelper;
import xyz.moviecast.base.models.Movie;
import xyz.moviecast.base.helpers.MovieHelper.MovieHelperResult;

public class MovieViewHolder extends RecyclerView.ViewHolder implements MovieHelper.MovieHelperCallback {

    private static final String TAG = "MOVIE_VIEW_HOLDER";

    private MovieView movieView;
    private MovieHelper movieHelper;

    public MovieViewHolder(View itemView, MovieHelper movieHelper) {
        super(itemView);
        this.movieView = (MovieView) itemView;
        this.movieHelper = movieHelper;
    }

    public void setMovie(String sorting, int position){
        movieHelper.getMovie(sorting, position, this);
    }

    @Override
    public void onFailure(int id, IOException e) {

    }

    @Override
    public void onResponse(int id, MovieHelperResult result) {
        MainActivity.getInstance().runOnUiThread(()->movieView.setMovie((Movie) result.getData()));
        Log.d(TAG, "onResponse: Movie has been shown: " + ((Movie) result.getData()).getTitle());
    }
}
