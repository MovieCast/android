/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.base;

import android.app.Application;
import android.content.Context;

import okhttp3.OkHttpClient;
import io.moviecast.base.managers.ProviderManager;

public interface BaseApplicationComponent {

    Application application();

    Context context();

    OkHttpClient okHttpClient();

    ProviderManager providerManager();



}
