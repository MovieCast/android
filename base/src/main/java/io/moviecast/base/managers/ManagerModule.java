/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.base.managers;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.moviecast.base.providers.MovieProvider;
import io.moviecast.base.providers.ShowProvider;

@Module
public class ManagerModule {

    @Provides
    @Singleton
    ProviderManager provideProviderManager(MovieProvider movieProvider, ShowProvider showProvider) {
        return new ProviderManager(movieProvider, showProvider);
    }

    @Provides
    @Singleton
    PreferenceManager providePreferenceManager(Context context) {
        return new PreferenceManager(context);
    }
}
