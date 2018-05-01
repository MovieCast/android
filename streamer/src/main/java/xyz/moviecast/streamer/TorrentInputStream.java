package xyz.moviecast.streamer;

import java.io.FilterInputStream;
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
}
