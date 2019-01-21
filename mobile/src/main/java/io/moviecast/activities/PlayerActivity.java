/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.moviecast.R;
import nl.timherreijgers.videoplayer.VideoPlayerFragment;

public class PlayerActivity extends AppCompatActivity {

    @BindView(R.id.video_player)
    FrameLayout videoPlayer;

    public static Intent getIntent(Context context) {
        return new Intent(context, PlayerActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);

        VideoPlayerFragment videoPlayerFragment = new VideoPlayerFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.video_player, videoPlayerFragment).commit();
        videoPlayerFragment.setVideoPath("https://vjs.zencdn.net/v/oceans.mp4");
    }
}
