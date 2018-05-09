package xyz.moviecast.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.VideoView;

import xyz.moviecast.R;

public class PlayerControlView extends FrameLayout implements View.OnClickListener {

    private static final String TAG = "PLAYER_CONTROL_VIEW";

    private onControlUsedListener listener;

    public PlayerControlView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_player_controller, this);

        findViewById(R.id.playButton).setOnClickListener(this);
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

    public enum VideoState{
        PLAYING, PAUSED
    }
}
