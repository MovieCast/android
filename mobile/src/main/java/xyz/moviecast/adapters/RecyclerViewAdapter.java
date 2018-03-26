package xyz.moviecast.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;

import xyz.moviecast.MovieCast;
import xyz.moviecast.activities.MainActivity;
import xyz.moviecast.base.helpers.MovieHelper;
import xyz.moviecast.base.models.Movie;
import xyz.moviecast.base.providers.MovieProvider;
import xyz.moviecast.views.MovieView;
import xyz.moviecast.views.MovieViewHolder;
import xyz.moviecast.base.helpers.MovieHelper.MovieHelperResult;

public class RecyclerViewAdapter extends RecyclerView.Adapter implements MovieHelper.MovieHelperCallback {

    private static final String TAG = "RECYCLER_ADAPTER";

    private int type;
    private String sorting;
    private static MovieHelper movieHelper;
    private static int size = 100;

    private static int counter = 0;

    public RecyclerViewAdapter(int type, String sorting, Context context) {
        this.type = type;
        this.sorting = sorting;
        movieHelper = MovieHelper.getInstance(context);
        MovieHelper.getInstance(context).getMovieListSize(this);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieView movieView = new MovieView(parent.getContext());
        MovieViewHolder movieViewHolder = new MovieViewHolder(movieView, movieHelper);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MovieViewHolder) holder).setMovie(sorting, position);
        Log.d(TAG, "onBindViewHolder: Created a holder: " + ++counter);
    }

    @Override
    public int getItemCount() {
        return size;
    }

    @Override
    public void onFailure(int id, IOException e) {
        Toast.makeText(MovieCast.getContext(), "Something went wrong, can't load the movies", Toast.LENGTH_SHORT).show();
        e.printStackTrace();
    }

    @Override
    public void onResponse(int id, MovieHelperResult result) {
        size = (Integer) result.getData();
        MainActivity.getInstance().runOnUiThread(()->{
            //notifyDataSetChanged();
        });

    }
}
