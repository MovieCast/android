/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.streamer.listeners;

import com.frostwire.jlibtorrent.AlertListener;
import com.frostwire.jlibtorrent.alerts.AddTorrentAlert;
import com.frostwire.jlibtorrent.alerts.Alert;
import com.frostwire.jlibtorrent.alerts.AlertType;

public abstract class AddTorrentAlertListener implements AlertListener {

    /**
     * Callback which gets called when a new torrent was added.
     *
     * @param alert The {@link AddTorrentAlert} with the torrent information
     */
    public abstract void onAddedTorrent(AddTorrentAlert alert);

    @Override
    public int[] types() {
        return new int[]{AlertType.ADD_TORRENT.swig()};
    }

    @Override
    public void alert(Alert<?> alert) {
        if(alert.type().equals(AlertType.ADD_TORRENT)) {
            onAddedTorrent((AddTorrentAlert) alert);
        }
    }
}
