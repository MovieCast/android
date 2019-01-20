/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.base.providers;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class ProviderModule {

    @Provides
    @Singleton
    MovieProvider provideMovieProvider(OkHttpClient client, Gson gson) {
        return new MovieProvider(client, gson);
    }

    @Provides
    @Singleton
    ShowProvider provideShowProvider(OkHttpClient client, Gson gson) {
        return new ShowProvider(client, gson);
    }
}
