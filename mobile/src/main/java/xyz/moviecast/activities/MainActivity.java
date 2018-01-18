package xyz.moviecast.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import xyz.moviecast.R;
import xyz.moviecast.base.Helpers.MovieHelper;
import xyz.moviecast.base.models.Movie;

public class MainActivity extends AppCompatActivity implements MovieHelper.OnMoviesDoneListener{

    private static final String TAG = "MAIN_ACTIVITY";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieHelper helper = new MovieHelper(this);
        helper.getMoviesOnPage(1, this);

        textView = findViewById(R.id.textView);
    }

    @Override
    public void onMoviesDone(final Movie[] movies) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("A total of " + movies.length + " movies were found on the first page\n" +
                        "The name of the first movie is " + movies[0].getTitle());
            }
        });
    }
}
