/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.streamer.utils;

import android.os.Handler;
import android.os.Looper;

public final class ThreadUtils {

    /**
     * Execute the given {@link Runnable} on the ui thread.
     * @param runnable The runnable to execute
     */
    public static void runOnUiThread(Runnable runnable) {
        Looper uiLooper = Looper.getMainLooper();
        if (Thread.currentThread() != uiLooper.getThread()) {
            new Handler(uiLooper).post(runnable);
        } else {
            runnable.run();
        }
    }
}
