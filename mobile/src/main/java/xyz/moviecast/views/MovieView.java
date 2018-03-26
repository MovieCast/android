package xyz.moviecast.views;

import android.content.Context;
import android.graphics.BitmapFactory;
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
        setImageResource(R.drawable.deadpool);
    }

    //TODO: show the correct image
    public void setMovie(Movie movie){
//        byte[] data = movie.getPosterImageData();
//        setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
        setImageResource(R.drawable.deadpool);
    }
}
