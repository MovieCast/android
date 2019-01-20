/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

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
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.moviecast.R;
import xyz.moviecast.base.models.Media;
import xyz.moviecast.base.utils.ImageUtils;

public class MediaGridAdapter extends RecyclerView.Adapter<MediaGridAdapter.ViewHolder> {

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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        params.width = itemWidth;
        params.height = itemHeight;
        holder.itemView.setLayoutParams(params);

        Media item = getItem(position);

        // We need the item later so add it to the view holder
        holder.title.setText(item.getTitle());
        holder.year.setText(item.getYear());


        if(item.getPosterImageUrl() != null && !item.getPosterImageUrl().equals("")) {
//            Picasso.get().setLoggingEnabled(true);
//            Picasso.get().cancelRequest(holder.coverImage);
//            Log.d("img", item.getPosterImageUrl());
//            Picasso.get().load(item.getPosterImageUrl())
//                         .resize(itemWidth, itemHeight)
//                         .transform(GradientTransformation.getInstance())
//                         .into(holder.coverImage);

            ImageUtils.loadAndProcess(holder.coverImage, item.getPosterImageUrl(),
                    new GradientPostprocessor(item.getId()));
//            holder.coverImage.setImageURI(item.getPosterImageUrl());
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

    private static class GradientPostprocessor extends BasePostprocessor {
        private String id;

        GradientPostprocessor(String id) {
            this.id = id;
        }

        @Override
        public void process(Bitmap source) {
            //super.process(bitmap);
            final int width = source.getWidth();
            final int height = source.getHeight();
            final float gradientHeight = height / 2f;

            Canvas canvas = new Canvas(source);

            Paint paint = new Paint();
            LinearGradient shader = new LinearGradient(0, height - gradientHeight, 0, height, 0xFFFFFFFF, 0x00FFFFFF, Shader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

            canvas.drawRect(0, height - gradientHeight, width, height, paint);
        }

        @Override
        public CacheKey getPostprocessorCacheKey() {
            return new SimpleCacheKey("id="+id);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item_poster)
        SimpleDraweeView coverImage;
        @BindView(R.id.item_title)
        TextView title;
        @BindView(R.id.item_year)
        TextView year;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            coverImage.setMinimumHeight(itemHeight);
            itemView.setOnClickListener(this);
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
