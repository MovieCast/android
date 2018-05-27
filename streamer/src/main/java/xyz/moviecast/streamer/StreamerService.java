package xyz.moviecast.streamer;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class StreamerService extends IntentService {

    private static final String TAG = "STREAMER_SERVICE";

    private Streamer streamer;

    public StreamerService() {
        super("StreamerService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

        streamer = Streamer.getInstance();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return START_STICKY;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent");
    }

    public void startStream(String hash) {
        Log.d(TAG, "startTorrent");

        if(streamer.isStreaming()) return;

        Log.d(TAG, "Starting stream");

        // WakeLock

        streamer.start(hash);
    }

    public void stopStream() {
        Log.d(TAG, "stopStream");
        streamer.stop();
    }

    public static void start(Context context) {
        Intent streamerIntent = new Intent(context, StreamerService.class);
        context.startService(streamerIntent);
    }

    public static void stop() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
