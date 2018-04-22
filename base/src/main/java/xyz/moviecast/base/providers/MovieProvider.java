package xyz.moviecast.base.providers;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import xyz.moviecast.base.providers.models.movies.Movie;
import xyz.moviecast.base.providers.models.movies.Page;

public class MovieProvider extends MediaProvider<Movie> {

//    private static final String URL_PAGE = "http://staging.content.moviecast.xyz/movies/";
//    private static final String URL_DETAIL = "http://staging.content.moviecast.xyz/movies/detail/";

    public MovieProvider(OkHttpClient client, ObjectMapper mapper) {
        super(client, mapper, "http://staging.content.moviecast.xyz", "/movies/", "/detail/");
    }

//    @Override
//    public Call getTotalAmountOfMedia(Callback callback) {
//        Request request = new Builder()
//                .url(URL_PAGE + "1")
//                .cacheControl(new CacheControl.Builder()
//                        .maxAge(7, TimeUnit.DAYS)
//                        .build())
//                .build();
//        Call call = client.newCall(request);
//        call.enqueue(callback);
//        return call;
//    }
//
//    @Override
//    public Call providePage(int page, String sorting, Callback callback) {
//        Request request = new Builder()
//                .url(URL_PAGE + page + "?sort=" + sorting)
//                .cacheControl(new CacheControl.Builder()
//                        .maxAge(7, TimeUnit.DAYS)
//                        .build())
//                .build();
//
//        Call call = client.newCall(request);
//        call.enqueue(callback);
//        return call;
//    }

    //TODO: Implement Method
//    @Override
//    public Movie provideDetails(@NonNull Movie object){
//        throw new RuntimeException("Not yet implemented!");
//        Request request = new Builder()
//                .url(URL_DETAIL + object.getId())
//                .cacheControl(new CacheControl.Builder()
//                        .maxAge(7, TimeUnit.DAYS)
//                        .build())
//                .build();
//
//        Response response = client.newCall(request).execute();
//        String body = response.body().string();
//        return mapper.readValue(body, Movie.class);
//    }

    @Override
    List<Movie> formatList(String response) {
        try {
            Page page = mapper.readValue(response, Page.class);
            return page.getMovies();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // UHMMM??
        return null;
    }
}
