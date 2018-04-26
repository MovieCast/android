package xyz.moviecast.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.widget.VideoView;

import xyz.moviecast.R;

public class PlayerActivity extends AppCompatActivity {

    private VideoView videoView;

    public static Intent getIntent(Context context) {
        return new Intent(context, PlayerActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        setSupportActionBar(null);
        videoView = findViewById(R.id.videoView);
        videoView.setVideoPath("http://vjs.zencdn.net/v/oceans.mp4");
        videoView.setOnClickListener((e) -> click());
        Toast.makeText(this, "The player can pause: " + videoView.canPause(), Toast.LENGTH_LONG).show();
    }

    public void click(){
        if(videoView.isPlaying())
            videoView.pause();
        else
            videoView.start();
    }
}
