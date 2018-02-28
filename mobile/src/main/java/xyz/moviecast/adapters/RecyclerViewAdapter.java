package xyz.moviecast.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import xyz.moviecast.views.MovieView;
import xyz.moviecast.views.MovieViewHolder;

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private int type;
    private String sorting;

    public RecyclerViewAdapter(int type, String sorting) {
        this.type = type;
        this.sorting = sorting;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieView movieView = new MovieView(parent.getContext());
        MovieViewHolder movieViewHolder = new MovieViewHolder(movieView);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MovieViewHolder) holder).setMovie(null);
    }

    @Override
    public int getItemCount() {
        return 50;
    }
}
