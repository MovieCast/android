/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.streamer.torrent;

import android.util.Log;

import com.frostwire.jlibtorrent.FileStorage;
import com.frostwire.jlibtorrent.Priority;
import com.frostwire.jlibtorrent.TorrentInfo;

public class TorrentHelper {
    private static final String TAG = "TORRENT_HELPER";

    private Torrent torrent;

    private int prioritizedFileIndex;

    private int firstPieceIndex = -1;
    private int lastPieceIndex = -1;

    public TorrentHelper(Torrent torrent) {
        this.torrent = torrent;
    }

    public int getPrioritizedFileIndex() {
        return prioritizedFileIndex;
    }

    public int getFirstPieceIndex() {
        return firstPieceIndex;
    }

    public int getLastPieceIndex() {
        return lastPieceIndex;
    }

    public void prioritizeFile(int index) {
        if(prioritizedFileIndex == index) return;

        TorrentInfo info = torrent.getHandle().torrentFile();
        FileStorage storage = info.files();

        // Find largest file ourselves
        if(index == -1) {
            long highestSize = 0;
            int selectedFile = -1;

            for(int i = 0; i < storage.numFiles(); i++) {
                long size = storage.fileSize(i);
                if(highestSize < size) {
                    highestSize = size;

                    torrent.getHandle().filePriority(selectedFile, Priority.IGNORE);
                    selectedFile = i;
                    torrent.getHandle().filePriority(selectedFile, Priority.NORMAL);
                } else {
                    torrent.getHandle().filePriority(i, Priority.IGNORE);
                }
            }
            index = selectedFile;
        } else {
            for(int i = 0; i < storage.numFiles(); i++) {
                if(i == index) {
                    torrent.getHandle().filePriority(i, Priority.NORMAL);
                } else {
                    torrent.getHandle().filePriority(i, Priority.IGNORE);
                }
            }
        }

        prioritizedFileIndex = index;

        initializePriorities();
    }

    public void initializePriorities() {
        int firstIndex = -1;
        int lastIndex = -1;
        Priority[] priorities = torrent.getHandle().piecePriorities();

        for(int i = 0; i < priorities.length; i++) {
            if(firstIndex == -1 && priorities[i] != Priority.IGNORE) {
                firstIndex = i;
                priorities[i] = Priority.IGNORE;
            } else if(lastIndex == -1 && priorities[i] == Priority.IGNORE) {
                lastIndex = i - 1;
            }
        }

        if(lastIndex < 0) {
            lastIndex = priorities.length - 1;
        }

        firstPieceIndex = firstIndex;
        lastPieceIndex = lastIndex;
        Log.d(TAG, "initializePriorities: first: " + firstPieceIndex + ", last: " + lastPieceIndex + ", total: " + torrent.getHandle().torrentFile().pieceLength());
    }

    public void resetPriorities() {
        Priority[] priorities = torrent.getHandle().piecePriorities();

        for(int i = 0; i < priorities.length; i++) {
            if(i >= firstPieceIndex && i <= lastPieceIndex) {
                torrent.getHandle().piecePriority(i, Priority.NORMAL);
            } else {
                torrent.getHandle().piecePriority(i, Priority.IGNORE);
            }
        }
    }

    public void preparePiecePriorities() {

    }

    @Override
    public String toString() {
        return "TorrentHelper{" +
                //"torrent=" + torrent +
                ", prioritizedFileIndex=" + prioritizedFileIndex +
                ", firstPieceIndex=" + firstPieceIndex +
                ", lastPieceIndex=" + lastPieceIndex +
                '}';
    }
}
