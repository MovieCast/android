package xyz.moviecast.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import xyz.moviecast.R;
import xyz.moviecast.base.models.Media;
import xyz.moviecast.base.models.Movie;
import xyz.moviecast.base.providers.MediaProvider;

public class MediaGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int itemWidth;
    private int itemHeight;
    private int columns;

    private final List<Media> result = new ArrayList<>();

    private OnItemClickListener itemClickListener;

    /**
     *
     * @param context
     * @param columns
     */
    public MediaGridAdapter(Context context, int columns) {
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
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        params.width = itemWidth;
        params.height = itemHeight;
        holder.itemView.setLayoutParams(params);

        Media item = getItem(position);

        // We need the item later so add it to the view holder
        ((ViewHolder) holder).media = item;
        ((ViewHolder) holder).title.setText(item.getTitle());
        ((ViewHolder) holder).year.setText(item.getYear());


        if(item.getPosterImageUrl() != null && !item.getPosterImageUrl().equals("")) {
            Picasso.get().cancelRequest(((ViewHolder) holder).coverImage);
            Picasso.get().load(item.getPosterImageUrl())
                         .resize(itemWidth, itemHeight)
                         .transform(GradientTransformation.getInstance())
                         .into(((ViewHolder) holder).coverImage);
        }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public Media getItem(int position) {
        if(position < 0 || position >= result.size()) {
            return null;
        }

        return result.get(position);
    }

    public void setResult(Set<Media> newResult) {
        result.clear();
        result.addAll(newResult);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }

    private static class GradientTransformation implements Transformation {
        private static GradientTransformation instance = new GradientTransformation();

        public static GradientTransformation getInstance() {
            return instance;
        }

        @Override
        public Bitmap transform(Bitmap source) {
            int width = source.getWidth();
            int height = source.getHeight();
            float gradientHeight = height / 2f;

            Bitmap overlay = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(overlay);

            canvas.drawBitmap(source, 0, 0, null);
            source.recycle();

            Paint paint = new Paint();
            LinearGradient shader = new LinearGradient(0,  height - gradientHeight, 0, height, 0xFFFFFFFF, 0x00FFFFFF, Shader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawRect(0, height - gradientHeight, width, height, paint);

            return overlay;
        }

        @Override
        public String key() {
            return "gradient";
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView coverImage;
        TextView title;
        TextView year;

        Media media;

        ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            coverImage = itemView.findViewById(R.id.item_poster);
            coverImage.setMinimumHeight(itemHeight);

            title = itemView.findViewById(R.id.item_title);
            year = itemView.findViewById(R.id.item_year);
        }

        public Media getMedia() {
            return media;
        }

        @Override
        public void onClick(View v) {
            if(itemClickListener != null) {
                itemClickListener.onItemClick(v, getItem(getLayoutPosition()));
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, Media media);
    }
}
