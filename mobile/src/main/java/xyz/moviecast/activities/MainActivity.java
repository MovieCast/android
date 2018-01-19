package xyz.moviecast.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

import xyz.moviecast.R;
import xyz.moviecast.base.Helpers.MovieHelper;
import xyz.moviecast.base.models.Movie;

public class MainActivity extends AppCompatActivity implements MovieHelper.MovieHelperCallbacks {

    private static final String TAG = "MAIN_ACTIVITY";

    private ImageView textView;
    private MovieHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new MovieHelper(this);

        helper.getMoviesOnPage(1, this);

        textView = findViewById(R.id.textView);
    }

    @Override
    public void onMoviesDone(final Movie[] movies) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                textView.setText("A total of " + movies.length + " movies were found on the first page\n" +
//                        "The name of the first movie is " + movies[0].getTitle());
//            }
//        });
        helper.getMoviePosterImage(movies[3], this);
    }

    @Override
    public void onImageDone(final byte[] imageData, String id, int type) {
        final Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setImageBitmap(bitmap);
            }
        });
    }
}
