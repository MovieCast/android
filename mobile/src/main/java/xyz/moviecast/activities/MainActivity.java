package xyz.moviecast.activities;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import xyz.moviecast.R;
import xyz.moviecast.base.entities.Movie;
import xyz.moviecast.base.providers.MoviesProvider;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAIN_ACTIVITY";

    protected ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MoviesProvider provider = new MoviesProvider();
                    Movie[] movies = provider.loadPage(1);
                    Log.d(TAG, "onCreate: The first movie in the array is: " + movies[0].toString());
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
