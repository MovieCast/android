package xyz.moviecast.activities;

import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;

import xyz.moviecast.R;
import xyz.moviecast.base.Constants;
import xyz.moviecast.base.helpers.HelperCallback;
import xyz.moviecast.base.helpers.HelperResult;
import xyz.moviecast.base.helpers.ImageHelper;
import xyz.moviecast.base.helpers.MovieHelper;
import xyz.moviecast.base.models.Movie;

public class MainActivityDebug extends AppCompatActivity implements HelperCallback {

    private ImageView imageView;
    private int id = 0;

    @Override
    protected void onStart() {
        super.onStart();
        Constants.context = this;
        Constants.application = getApplication();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_debug);

        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener((view)->requestMovie());
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        requestMovie();
    }

    private void requestMovie(){
        MovieHelper.getInstance().getMovie("trending", id++, this);
    }

    @Override
    public void onFailure(int id, IOException e) {

    }

    @Override
    public void onResponse(int id, HelperResult result) {
        if(result.getData() instanceof Movie)
            ImageHelper.getInstance().getImage(((Movie) result.getData()).getPosterImageUrl(), this);
        else if (result.getData() instanceof byte[]){
            runOnUiThread(()->{
                byte[] data = (byte[]) result.getData();
                imageView.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
            });
        }
    }
}
