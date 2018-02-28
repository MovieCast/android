package xyz.moviecast.views;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;

import xyz.moviecast.R;
import xyz.moviecast.base.models.Movie;

/**
 * Class description  here
 *
 * @author Tim
 */

public class MovieView extends AppCompatImageView {

    public MovieView(Context context) {
        super(context);
        setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));
        setScaleType(ScaleType.FIT_START);
        setAdjustViewBounds(true);
    }

    public void setMovie(Movie movie){
        setImageResource(R.drawable.deadpool);
    }
}
