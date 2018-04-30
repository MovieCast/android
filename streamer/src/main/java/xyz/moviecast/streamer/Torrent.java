package xyz.moviecast.streamer;

import android.util.Log;

import com.frostwire.jlibtorrent.TorrentHandle;

import java.io.File;

public class Torrent {
    private TorrentHandle handle;

    public Torrent(TorrentHandle handle) {
        this.handle = handle;
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
     * Start the torrent.
     */
    public void start() {
        // TODO: Write start logic
        Log.d("MOVIECAST_STREAMER_TORR", "Start method was called");
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
}
