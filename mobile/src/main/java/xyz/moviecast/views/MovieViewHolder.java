package xyz.moviecast.views;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import xyz.moviecast.activities.MainActivity;
import xyz.moviecast.base.helpers.HelperResult;
import xyz.moviecast.base.helpers.MovieHelper;
import xyz.moviecast.base.models.Movie;
import xyz.moviecast.base.helpers.HelperCallback;

public class MovieViewHolder extends RecyclerView.ViewHolder implements HelperCallback {

    private static final String TAG = "MOVIE_VIEW_HOLDER";

    private MovieView movieView;
    private MovieHelper movieHelper;
    private int lastId;
    private String sorting;
    private int position;

    public MovieViewHolder(View itemView, MovieHelper movieHelper) {
        super(itemView);
        this.movieView = (MovieView) itemView;
        this.movieHelper = movieHelper;
    }

    public void setMovie(String sorting, int position){
        HelperResult result = movieHelper.getMovie(sorting, position, this);
        if (result.getData() instanceof Integer)
            lastId = (Integer) result.getData();
        else if (result.getData() instanceof Movie)
            changeMovie((Movie) result.getData());

        this.sorting = sorting;
        this.position = position;
    }

    private void changeMovie(Movie movie){
        movieView.setMovie(movie);
    }

    @Override
    public void onFailure(int id, IOException e) {
        e.printStackTrace();
    }

    @Override
    public void onResponse(int id, HelperResult result) {
        Log.d(TAG, "onResponse() called with: id = [" + id + "], result = [" + result + "]");
        if(lastId == id)
            changeMovie((Movie) result.getData());
    }
}
