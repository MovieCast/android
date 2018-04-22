package xyz.moviecast.adapters;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import xyz.moviecast.R;
import xyz.moviecast.base.models.Movie;

public class MediaGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int itemWidth;
    private int itemHeight;
    private int columns;

    private ArrayList<Movie> items = new ArrayList<>();

    /**
     *
     * @param context
     * @param items
     * @param columns
     */
    public MediaGridAdapter(Context context, ArrayList<Movie> items, int columns) {
        this.columns = columns;

        // Sorry little bit dirty atm...
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        // Calculate correct item ratio
        int screenWidth = size.x;
        itemWidth = (screenWidth / columns);
        itemHeight = (int) (itemWidth / 0.677);

        //setItems(items);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        params.width = itemWidth;
        params.height = itemHeight;
        holder.itemView.setLayoutParams(params);

        Movie item = getItem(position);
        if(item.getPosterImageUrl() != null && !item.getPosterImageUrl().equals("")) {
            Picasso.get().cancelRequest(((ViewHolder) holder).coverImage);
            Picasso.get().load(item.getPosterImageUrl())
                         .resize(itemWidth, itemHeight)
                         .into(((ViewHolder) holder).coverImage);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Movie getItem(int position) {
        if(position < 0 || position >= items.size()) {
            return null;
        }

        return items.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView coverImage;

        public ViewHolder(View itemView) {
            super(itemView);

            coverImage = itemView.findViewById(R.id.cover_image);
            coverImage.setMinimumHeight(itemHeight);
        }

        public ImageView getCoverImage() {
            return coverImage;
        }
    }
}
