package xyz.moviecast.base.providers;

import android.content.Context;
import android.os.Build;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import xyz.moviecast.base.providers.models.movies.Images;
import xyz.moviecast.base.providers.models.movies.Movie;

public class ImageProvider extends BaseProvider {

    public static final int POSTER_IMAGE = 1;
    public static final int BACKGROUND_IMAGE = 2;

    public ImageProvider(Context context) {
        super(context);
    }

    public byte[] provideImage(Movie movie, int type) throws IOException{
        if(movie == null)
            return null;

        Images images = movie.getImages();
        if(images == null){
            return null;
        }

        if(type == POSTER_IMAGE)
            return provideImage(images.getPosterImage());
        if(type == BACKGROUND_IMAGE)
            return provideImage(images.getBackgroundImage());
        return null;
    }

    private byte[] provideImage(String url) throws IOException{
        Request request = new Builder()
                .url(url)
                .cacheControl(new CacheControl.Builder()
                        .maxAge(7, TimeUnit.DAYS)
                        .build())
                .build();

        Response response = client.newCall(request).execute();
        return response.body().bytes();
    }
}
