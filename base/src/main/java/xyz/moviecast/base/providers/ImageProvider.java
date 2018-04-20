package xyz.moviecast.base.providers;

import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Request.Builder;

public class ImageProvider extends BaseProvider {

    public Call provideImage(String imageUrl, Callback callback){
        Request request = new Builder()
                .url(imageUrl)
                .cacheControl(new CacheControl.Builder()
                        .maxAge(7, TimeUnit.DAYS)
                        .build())
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }
}
