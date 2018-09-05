package xyz.moviecast.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import xyz.moviecast.R;

public class PlayerControlView extends FrameLayout implements View.OnClickListener {

    private static final String TAG = "PLAYER_CONTROL_VIEW";

    private onControlUsedListener listener;

    private ImageButton playButton;
    private TextView currentTime;
    private TextView totalTime;
    private ProgressBar progressBar;

    public PlayerControlView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_player_controller, this);

        playButton = findViewById(R.id.playButton);
        currentTime = findViewById(R.id.currentTimeTextView);
        totalTime = findViewById(R.id.totalTimeTextView);
        progressBar = findViewById(R.id.progressBar);

        playButton.setOnClickListener(this);
    }

    public void setOnControlUsedListener(onControlUsedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.playButton:
                if(listener != null)
                    if(listener.onPlayButtonPressed() == VideoState.PLAYING)
                        Log.d(TAG, "onClick: Video is playing");
                    else
                        Log.d(TAG, "onClick: Video is paused");
                break;
        }
    }

    public interface onControlUsedListener{
        VideoState onPlayButtonPressed();
    }

    public void setVideoStream(){

    }

    public enum VideoState{
        PLAYING, PAUSED
    }
}
