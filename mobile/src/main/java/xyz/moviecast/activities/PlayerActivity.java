package xyz.moviecast.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import xyz.moviecast.R;
import xyz.moviecast.views.PlayerView;

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener {

    private PlayerView playerView;
    private Toolbar toolbar;

    public static Intent getIntent(Context context) {
        return new Intent(context, PlayerActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        toolbar = findViewById(R.id.toolbar);
        playerView = findViewById(R.id.playerView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        playerView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(getSupportActionBar().isShowing())
            getSupportActionBar().hide();
        else
            getSupportActionBar().show();
    }
}
