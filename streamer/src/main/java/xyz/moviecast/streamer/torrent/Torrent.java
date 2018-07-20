/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.streamer.torrent;

import android.support.annotation.MainThread;
import android.util.Log;

import com.frostwire.jlibtorrent.AlertListener;
import com.frostwire.jlibtorrent.FileStorage;
import com.frostwire.jlibtorrent.Priority;
import com.frostwire.jlibtorrent.TorrentFlags;
import com.frostwire.jlibtorrent.TorrentHandle;
import com.frostwire.jlibtorrent.TorrentInfo;
import com.frostwire.jlibtorrent.alerts.Alert;
import com.frostwire.jlibtorrent.alerts.AlertType;
import com.frostwire.jlibtorrent.alerts.TorrentErrorAlert;

import java.io.File;

public class Torrent implements AlertListener {

    private static final String TAG = "MOVIECAST_TORRENT";

    public enum State { METADATA, STARTING, STREAMING }

    private int selectedFileIndex;

    private TorrentHandle handle;
    private TorrentListener listener;

    private State state = State.METADATA;

    public Torrent(TorrentHandle handle, TorrentListener listener) {
        this.handle = handle;
        this.listener = listener;

        setLargestFile();

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
        return new File(handle.savePath() + "/" + handle.torrentFile().files().filePath(selectedFileIndex));
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

    public void setLargestFile() {
        setSelectedFileIndex(-1);
    }

    public void setSelectedFileIndex(int index) {
        TorrentInfo info = handle.torrentFile();
        FileStorage storage = info.files();

        // Find largest file ourselves
        if(index == -1) {
            long highestSize = 0;
            int selectedFile = -1;

            for(int i = 0; i < storage.numFiles(); i++) {
                long size = storage.fileSize(i);
                if(highestSize < size) {
                    highestSize = size;

                    handle.filePriority(selectedFile, Priority.IGNORE);
                    selectedFile = i;
                    handle.filePriority(selectedFile, Priority.NORMAL);
                } else {
                    handle.filePriority(i, Priority.IGNORE);
                }
            }
            index = selectedFile;
        } else {
            for(int i = 0; i < storage.numFiles(); i++) {
                if(i == index) {
                    handle.filePriority(i, Priority.NORMAL);
                } else {
                    handle.filePriority(i, Priority.IGNORE);
                }
            }
        }

        selectedFileIndex = index;
    }

    /**
     * Start the torrent.
     */
    public void start() {
        if(state == State.STREAMING) {
            return;
        }
        state = State.STREAMING;

        // Reset piece priorities
        Priority[] priorities = handle.piecePriorities();
        for(int i = 0; i < priorities.length; i++) {
            if(priorities[i] != Priority.IGNORE) {
                handle.piecePriority(i, Priority.NORMAL);
            }
        }

        // Prioritize pieces to prepare

        // Set progress

        handle.setFlags(handle.flags().and_(TorrentFlags.SEQUENTIAL_DOWNLOAD));

        handle.resume();

        if(listener != null) {
            listener.onStreamStarted(this);
        }
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
                AlertType.BLOCK_FINISHED.swig(),
                AlertType.TORRENT_ERROR.swig()
        };
    }

    @Override
    public void alert(Alert<?> alert) {
        switch (alert.type()) {
            case TORRENT_ERROR:
                Log.d(TAG, "alert: " + alert.what());
                Log.d(TAG, "alert: is paused = " + ((TorrentErrorAlert) alert).handle().status());
                break;
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

    @Override
    public String toString() {
        return "Torrent{" +
                "selectedFileIndex=" + selectedFileIndex +
                ", handle=" + handle +
                ", listener=" + listener +
                ", state=" + state +
                '}';
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
