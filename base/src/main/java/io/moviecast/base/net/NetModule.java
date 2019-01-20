/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.base.net;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

@Module
public class NetModule {

    @Provides
    @Singleton
    Cache provideCache(Context context) {
        return new Cache(context.getCacheDir(), 10*1024*1024);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .cache(cache)
                .build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

//    @Provides
//    @Singleton
//    ObjectMapper provideObjectMapper() {
//        return new ObjectMapper();
//    }

    //TODO: Add picasso with okhttpdownloader so it uses the same cache.
}
