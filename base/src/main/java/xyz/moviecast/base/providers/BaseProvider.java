package xyz.moviecast.base.providers;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import xyz.moviecast.base.Constants;

class BaseProvider {

    static OkHttpClient client;

    BaseProvider() {

        if(client == null){
            client = new OkHttpClient.Builder()
                    .cache(new Cache(Constants.context.getCacheDir(), 10*1024*1024))
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();
        }

    }
}
