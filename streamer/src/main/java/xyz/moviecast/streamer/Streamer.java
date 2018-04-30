package xyz.moviecast.streamer;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.frostwire.jlibtorrent.AlertListener;
import com.frostwire.jlibtorrent.SessionManager;
import com.frostwire.jlibtorrent.alerts.AddTorrentAlert;

import xyz.moviecast.streamer.listeners.AddTorrentAlertListener;

public class Streamer {

    private boolean streaming = false;

    private final SessionManager torrentSession;

    // Threads
    private HandlerThread libTorrentThread;
    private Handler libTorrentHandler;

    private HandlerThread streamerThread;
    private Handler streamerHandler;

    Streamer(SessionManager sessionManager) {
        torrentSession = sessionManager;

        torrentSession.addListener(new AddTorrentAlertListener() {
            @Override
            public void onAddedTorrent(AddTorrentAlert alert) {
                Log.d("MOVIECAST_STREAMER", "A new torrent was added: " + alert.toString());

                Torrent torrent = new Torrent(alert.handle());
                torrent.start();
            }
        });

        libTorrentThread = new HandlerThread("MOVIECAST_LIBTORRENT");
        libTorrentThread.start();
        libTorrentHandler = new Handler(libTorrentThread.getLooper());
        libTorrentHandler.post(new Runnable() {
            @Override
            public void run() {
                // initialize SettingsPack of LibTorrent

                // add dth listener for information
                // start dth
            }
        });
    }

    public void start(String hash) {
        if(streaming) return;

        streamerThread = new HandlerThread("MOVIECAST_STREAMER");
        streamerThread.start();
        streamerHandler = new Handler(streamerThread.getLooper());

        streamerHandler.post(new Runnable() {
            @Override
            public void run() {
                streaming = true;


            }
        });
    }

    public void pause() {
        libTorrentHandler.post(torrentSession::pause);
    }

    public void resume() {
        // First make sure all callbacks and messages get canceled.
        libTorrentHandler.removeCallbacksAndMessages(null);

        if(torrentSession.isPaused()) {
            libTorrentHandler.post(torrentSession::resume);
        }
    }

    public void stop() {
        if(!streaming) return;

        libTorrentHandler.removeCallbacksAndMessages(null);
        streamerHandler.removeCallbacksAndMessages(null);

        streaming = false;

        // TODO: Write torrent logic over here

        streamerThread.interrupt();

    }
}
