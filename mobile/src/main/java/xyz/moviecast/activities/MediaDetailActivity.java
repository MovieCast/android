package xyz.moviecast.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import xyz.moviecast.R;
import xyz.moviecast.base.models.Media;

public class MediaDetailActivity extends AppCompatActivity {

    public static final String MEDIA_OBJECT = "MEDIA_OBJECT";

    private Toolbar toolbar;
    private ImageView poster;

    private Media media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_detail);

        toolbar = findViewById(R.id.toolbar);
        poster = findViewById(R.id.detail_poster);

        media = (Media) getIntent().getSerializableExtra(MEDIA_OBJECT);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(media.getTitle());


        // TODO: Move picasso to NetModule

        Picasso.get().load(media.getPosterImageUrl()).into(poster);
    }
}
