package xyz.moviecast.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.ArrayList;

import xyz.moviecast.base.models.Movie;

public class MovieCatalogView extends GridLayout {

    private Context context;
    private ArrayList<Movie> movies;
    private int margin;
    private int imageWidth;

    private static final String TAG = "MOVIE_CATALOG";

    public MovieCatalogView(Context context) {
        this(context, null);
    }

    public MovieCatalogView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        movies = new ArrayList<>();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        margin = Math.round(w / 100f * 0.5f);
        imageWidth = (w - 4 * margin) / 2;
    }

    public void addMovie(Movie movie){
        if(movie == null)
            return;

        ImageView imageView = new ImageView(context);
        byte[] imageData = movie.getPosterImageData();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float ratio = imageWidth / (float) width;
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, Math.round(width * ratio), Math.round(height * ratio), true);
        imageView.setImageBitmap(scaledBitmap);
        LayoutParams params = new LayoutParams();
        params.width = LayoutParams.WRAP_CONTENT;
        params.height = LayoutParams.WRAP_CONTENT;
        params.setMargins(margin, margin, margin, margin);
        addView(imageView, params);
        movies.add(movie);
        Log.d(TAG, "addMovie: Movie added: " + movie.getTitle());
    }
}
