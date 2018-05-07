package xyz.moviecast.base.utils;

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