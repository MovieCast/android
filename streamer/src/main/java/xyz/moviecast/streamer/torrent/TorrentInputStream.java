/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.streamer.torrent;

import androidx.annotation.NonNull;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

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

    @Override
    public int read(@NonNull byte[] b, int off, int len) throws IOException {

        // TODO: Tell Streamer to prioritize byte array

        return super.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {

        // TODO: Update torrent pieces

        return super.skip(n);
    }
}
