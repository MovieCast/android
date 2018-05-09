package xyz.moviecast.views;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.VideoView;

import xyz.moviecast.R;

public class PlayerView extends ConstraintLayout implements PlayerControlView.onControlUsedListener, View.OnClickListener {

    private VideoView videoView;
    private PlayerControlView controlView;
    private boolean paused = true;

    public PlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_player, this);

        videoView = findViewById(R.id.videoView);
        controlView = findViewById(R.id.controller);

        videoView.setVideoPath("http://vjs.zencdn.net/v/oceans.mp4");
        videoView.start();
        paused = false;

        controlView.setOnControlUsedListener(this);
        videoView.setOnClickListener(this);
    }

    @Override
    public PlayerControlView.VideoState onPlayButtonPressed() {
        if(!paused) {
            videoView.pause();
            paused = true;
            return PlayerControlView.VideoState.PAUSED;
        }else {
            videoView.start();
            paused = false;
            return PlayerControlView.VideoState.PLAYING;
        }
    }

    @Override
    public void onClick(View v) {
        if(controlView.getVisibility() == VISIBLE)
            controlView.setVisibility(INVISIBLE);
        else
            controlView.setVisibility(VISIBLE);
    }
}
