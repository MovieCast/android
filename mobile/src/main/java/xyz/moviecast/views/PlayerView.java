package xyz.moviecast.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.VideoView;

import xyz.moviecast.R;

public class PlayerView extends LinearLayout {

    private VideoView videoView;

    public PlayerView(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_player, this);

        videoView = findViewById(R.id.videoView);
    }
}
