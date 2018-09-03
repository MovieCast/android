/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.activities;

import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.moviecast.R;
import xyz.moviecast.base.models.Media;
import xyz.moviecast.base.models.Movie;
import xyz.moviecast.base.models.Show;
import xyz.moviecast.fragments.MovieDetailFragment;
import xyz.moviecast.fragments.ShowDetailFragment;

public class MediaDetailActivity extends AppCompatActivity {

    public static final String MEDIA_OBJECT = "MEDIA_OBJECT";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.app_bar_layout)
    AppBarLayout appBar;

    @BindView(R.id.detail_poster)
    ImageView poster;

    //private FloatingActionButton button;
    private Media media;

    @Override
    @SuppressWarnings("MissingSuperCall")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_detail);

        ButterKnife.bind(this);

        //button = findViewById(R.id.flying_button);

        media = (Media) getIntent().getSerializableExtra(MEDIA_OBJECT);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) ->
                poster.setAlpha(1.0f - Math.abs(verticalOffset / (float) appBar.getTotalScrollRange())));

        if(media != null) {
            getSupportActionBar().setTitle(media.getTitle());

            // TODO: Move picasso to NetModule
            Picasso.get().load(media.getPosterImageUrl()).into(poster);

            FragmentManager fragmentManager = getSupportFragmentManager();
            if (media instanceof Movie) {
                fragmentManager.beginTransaction().replace(R.id.content, MovieDetailFragment.newInstance((Movie) media)).commit();
            }
            else if(media instanceof Show){
                fragmentManager.beginTransaction().replace(R.id.content, ShowDetailFragment.newInstance((Show) media)).commit();
                //button.hide();
            }
        }
    }
}
