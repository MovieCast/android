/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.base;

import android.app.Application;

public class BaseApplication extends Application {
    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
