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
import com.frostwire.jlibtorrent.alerts.BlockFinishedAlert;
import com.frostwire.jlibtorrent.alerts.PieceFinishedAlert;
import com.frostwire.jlibtorrent.alerts.TorrentErrorAlert;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Torrent implements AlertListener {

    private static final String TAG = "MOVIECAST_TORRENT";

    public enum State { METADATA, STARTING, STREAMING }


    private TorrentHandle handle;
    private TorrentHelper helper;
    private TorrentListener listener;

    private List<Integer> preparePieceIndexes = new ArrayList<>();

    private State state = State.METADATA;

    public Torrent(TorrentHandle handle, TorrentListener listener) {
        this.handle = handle;
        this.listener = listener;

        helper = new TorrentHelper(this);
        helper.prioritizeFile(-1); // Prioritize biggest file

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

    public InputStream getInputStream() {

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
        if(state != State.METADATA) {
            return;
        }

        state = State.STARTING;

        // Reset piece priorities
        Priority[] priorities = handle.piecePriorities();
        for(int i = 0; i < priorities.length; i++) {
            if(priorities[i] != Priority.IGNORE) {
                handle.piecePriority(i, Priority.NORMAL);
            }
        }

        preparePieceIndexes = new ArrayList<>();

        // Prioritize pieces to prepare
        for (int i = 0; i < 15; i++) {
            preparePieceIndexes.add(helper.getLastPieceIndex() - i);
            handle.piecePriority(helper.getLastPieceIndex() - i, Priority.SEVEN);
            handle.setPieceDeadline(helper.getLastPieceIndex() - i, 1000);

            preparePieceIndexes.add(helper.getFirstPieceIndex() + i);
            handle.piecePriority(helper.getFirstPieceIndex() + i, Priority.SEVEN);
            handle.setPieceDeadline(helper.getFirstPieceIndex() + i, 1000);
        }

        // Set progress

        //handle.setFlags(handle.flags().and_(TorrentFlags.SEQUENTIAL_DOWNLOAD));

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

    private void onPieceFinished(PieceFinishedAlert alert) {
        // We are still preparing the stream
        if(state == State.STARTING) {
            // Remove the finished piece
            Log.d(TAG, "onPieceFinished: removed " + alert.pieceIndex() + " from prepareList, prepareList contains: " + preparePieceIndexes.toString());
            preparePieceIndexes.remove(Integer.valueOf(alert.pieceIndex()));

            if(preparePieceIndexes.size() == 0) {
                Log.d(TAG, "onPieceFinished: state = STREAMING");
                state = State.STREAMING;

                if(listener != null) {
                    listener.onStreamReady(this);
                }
            }
        } else {
            Log.d(TAG, "onPieceFinished: " + alert);
        }
    }

    private void onBlockFinished(BlockFinishedAlert alert) {
        //Log.d(TAG, "onBlockFinished: " + alert);
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
                onPieceFinished((PieceFinishedAlert) alert);
                break;
            case BLOCK_FINISHED:
                onBlockFinished((BlockFinishedAlert) alert);
                break;
            default:
                Log.d(TAG, "alert: unsupported type: " + alert.type());
        }
    }

    @Override
    public String toString() {
        return "Torrent{" +
                ", handle=" + handle +
                ", helper=" + helper +
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
