package xyz.moviecast.streamer.torrent;

import android.support.annotation.MainThread;
import android.util.Log;

import com.frostwire.jlibtorrent.AlertListener;
import com.frostwire.jlibtorrent.TorrentHandle;
import com.frostwire.jlibtorrent.alerts.Alert;
import com.frostwire.jlibtorrent.alerts.AlertType;

import java.io.File;

public class Torrent implements AlertListener {

    private static final String TAG = "MOVIECAST_TORRENT";

    public enum State { METADATA, STARTING, STREAMING }

    private TorrentHandle handle;
    private TorrentListener listener;

    private State state = State.METADATA;

    public Torrent(TorrentHandle handle, TorrentListener listener) {
        this.handle = handle;
        this.listener = listener;

        if(this.listener != null) {
            this.listener.onStreamPrepared(this);
        }
    }

    /**
     * @return the {@link TorrentHandle} of this torrent.
     */
    public TorrentHandle getHandle() {
        return handle;
    }

    /**
     * @return the file that is being streamed.
     */
    public File getFile() {
        // TODO: Determine which of the files is the actual file to be streamed.
        return new File(handle.savePath() + "/" + handle.torrentFile().files().filePath(0));
    }

    /**
     * @return the save path of this torrent.
     */
    public File getSavePath() {
        return new File(handle.savePath() + "/" + handle.name());
    }

    /**
     * @return the current torrent state
     */
    public State getState() {
        return state;
    }

    /**
     * Start the torrent.
     */
    public void start() {
        // TODO: Write start logic
        Log.d(TAG, "Start method was called");
        handle.resume();
    }

    /**
     * Pause the torrent.
     */
    public void pause() {
        handle.pause();
    }

    /**
     * Resume the torrent.
     */
    public void resume() {
        handle.resume();
    }

    @Override
    public int[] types() {
        return new int[] {
                AlertType.PIECE_FINISHED.swig(),
                AlertType.BLOCK_FINISHED.swig()
        };
    }

    @Override
    public void alert(Alert<?> alert) {
        switch (alert.type()) {
            case PIECE_FINISHED:
                Log.d(TAG, "alert: " + alert.toString());
                break;
            case BLOCK_FINISHED:
                Log.d(TAG, "alert: " + alert.toString());
                break;
            default:
                Log.d(TAG, "alert: unsupported type: " + alert.type());
        }
    }

    /**
     * Interface definition for callbacks to be invoked when streamer events occur.
     */
    public interface TorrentListener {
        /**
         * Called when a torrent has been prepared.
         *
         * @param torrent The torrent that has been prepared
         */
        @MainThread
        void onStreamPrepared(Torrent torrent);

        /**
         * Called when a torrent has been started.
         *
         * @param torrent The torrent that is started
         */
        @MainThread
        void onStreamStarted(Torrent torrent);

        /**
         * Called when a torrent has been prepared and is ready to be streamed.
         *
         * @param torrent The torrent that is ready
         */
        @MainThread
        void onStreamReady(Torrent torrent);

        /**
         * Called when the progress state of a torrent changed.
         *
         * @param torrent The torrent on which the progress state was changed
         */
        @MainThread
        void onStreamProgress(Torrent torrent);

        /**
         * Called when a torrent caused an error to occur.
         *
         * @param torrent   The torrent the error occurred on
         * @param exception The exception that occurred
         */
        @MainThread
        void onStreamError(Torrent torrent, Exception exception);

        /**
         * Called when a stream was stopped.
         */
        @MainThread
        void onStreamStopped();
    }

}
