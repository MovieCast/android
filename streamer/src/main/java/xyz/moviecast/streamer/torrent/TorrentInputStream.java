/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.streamer.torrent;

import java.io.FilterInputStream;
import java.io.InputStream;

import xyz.moviecast.streamer.torrent.Torrent;

public class TorrentInputStream extends FilterInputStream {

    private Torrent torrent;

    /**
     * Creates a <code>FilterInputStream</code>
     * by assigning the  argument <code>in</code>
     * to the field <code>this.in</code> so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     *           this instance is to be created without an underlying stream.
     */
    protected TorrentInputStream(InputStream in, Torrent torrent) {
        super(in);

        this.torrent = torrent;
    }
}
