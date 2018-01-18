package xyz.moviecast.base.providers;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class BaseProvider {

    static OkHttpClient okHttpClient;

    public BaseProvider(Context context) {

        if(okHttpClient == null){
            okHttpClient = new OkHttpClient.Builder()
                    .cache(new Cache(context.getCacheDir(), 10*1024*1024))
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();
        }

    }
}
