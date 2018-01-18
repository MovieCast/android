package xyz.moviecast.base.providers;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import xyz.moviecast.base.providers.models.movies.Movie;
import xyz.moviecast.base.providers.models.movies.Page;

public class MovieProvider extends MediaProvider<Movie> {

    private static final String URL_PAGE = "https://content.moviecast.xyz/movies/";
    private static final String URL_DETAIL = "https://content.moviecast.xyz/movies/detail/";

    public MovieProvider(Context context) {
        super(context);
    }

    @Override
    public Page providePage(int page) throws IOException{
        Request request = new Builder()
                .url(URL_PAGE + page)
                .cacheControl(new CacheControl.Builder()
                        .maxAge(7, TimeUnit.DAYS)
                        .build())
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String body = response.body().string();
        return mapper.readValue(body, Page.class);
    }

    @Override
    public Movie provideDetails(Movie object) throws IOException{
        return null;
    }
}
