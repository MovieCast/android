package xyz.moviecast.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;

import xyz.moviecast.R;
import xyz.moviecast.activities.MainActivity;
import xyz.moviecast.base.Constants;
import xyz.moviecast.base.helpers.HelperCallback;
import xyz.moviecast.base.helpers.HelperResult;
import xyz.moviecast.base.helpers.ImageHelper;
import xyz.moviecast.base.models.Movie;

public class MovieView extends AppCompatImageView implements HelperCallback {

    private static final String TAG = "MOVIE_VIEW";

    private int lastId;
    private Movie movie;

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
        Log.d(TAG, "setMovie: Movie " + movie.getTitle() + " added.");
        this.movie = movie;

        HelperResult result = ImageHelper.getInstance().getImage(movie.getPosterImageUrl(), this);
        if(result.getData() instanceof Integer)
            lastId = (int) result.getData();
        else if(result.getData() instanceof byte[])
            setImageByteArray((byte[]) result.getData());

    }

    private void setImageByteArray(byte[] imageData){
        MainActivity.getInstance().runOnUiThread(() -> {
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            Bitmap bitmap = Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getHeight(), 
                    Bitmap.Config.ARGB_8888);
            setImageBitmap(bitmap);
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            canvas.drawBitmap(bitmap1, 0, 0, paint);
            canvas.drawText(movie.getTitle(), 10, 10, paint);
            setImageBitmap(bitmap);
        });
    }

    @Override
    public void onFailure(int id, IOException e) {
        MainActivity.getInstance().runOnUiThread(()->{
            setImageResource(R.drawable.deadpool);
            Log.d(TAG, "onFailure: Failed to load the movie image");
        });
    }

    @Override
    public void onResponse(int id, HelperResult result) {
        if(lastId == id) {
            if (result.getData() instanceof byte[]) {
                setImageByteArray((byte[]) result.getData());
            }
        }
    }
}
