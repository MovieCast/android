package xyz.moviecast.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import xyz.moviecast.base.helpers.MovieHelper;
import xyz.moviecast.base.providers.MovieProvider;
import xyz.moviecast.views.MovieView;
import xyz.moviecast.views.MovieViewHolder;
import xyz.moviecast.base.helpers.MovieHelper.MovieHelperResult;

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private static final String TAG = "RECYCLER_ADAPTER";

    private int type;
    private String sorting;
    private static MovieHelper movieHelper;
    private static int size = 0;

    public RecyclerViewAdapter(int type, String sorting, Context context) {
        this.type = type;
        this.sorting = sorting;
        movieHelper = new MovieHelper(context);
        new AdatapterTask().execute();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieView movieView = new MovieView(parent.getContext());
        MovieViewHolder movieViewHolder = new MovieViewHolder(movieView, movieHelper);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MovieViewHolder) holder).setMovie(null);
    }

    @Override
    public int getItemCount() {
        return size;
    }

    private class AdatapterTask extends AsyncTask<Void, Void, MovieHelperResult>{

        @Override
        protected MovieHelperResult doInBackground(Void... voids) {
            return movieHelper.getMovieListSize();
        }

        @Override
        protected void onPostExecute(MovieHelperResult movieHelperResult) {
            if(movieHelperResult != null){
                size = movieHelperResult.getIntRestult();
//                notifyDataSetChanged();
            }
        }
    }
}
