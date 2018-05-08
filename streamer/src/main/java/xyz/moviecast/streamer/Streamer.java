package xyz.moviecast.streamer;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.frostwire.jlibtorrent.AlertListener;
import com.frostwire.jlibtorrent.SessionManager;
import com.frostwire.jlibtorrent.SessionParams;
import com.frostwire.jlibtorrent.SettingsPack;
import com.frostwire.jlibtorrent.TorrentInfo;
import com.frostwire.jlibtorrent.alerts.AddTorrentAlert;

import java.io.File;
import java.util.concurrent.CountDownLatch;

import xyz.moviecast.streamer.listeners.AddTorrentAlertListener;
import xyz.moviecast.streamer.listeners.DHTStatsAlertListener;
import xyz.moviecast.streamer.utils.TorrentUtils;

public class Streamer {

    private static Streamer instance;

    public static Streamer getInstance() {
        if(instance == null) instance = new Streamer();
        return instance;
    }

    private CountDownLatch initLatch;

    private boolean streaming = false;

    private SessionManager torrentSession;

    // Threads
    private HandlerThread libTorrentThread;
    private Handler libTorrentHandler;

    private HandlerThread streamerThread;
    private Handler streamerHandler;

    Streamer() {

//        torrentSession.addListener(new AddTorrentAlertListener() {
//            @Override
//            public void onAddedTorrent(AddTorrentAlert alert) {
//                Log.d("MOVIECAST_STREAMER", "A new torrent was added: " + alert.toString());
//
//                Torrent torrent = new Torrent(alert.handle());
//                torrent.start();
//            }
//        });

        initLatch = new CountDownLatch(1);

        libTorrentThread = new HandlerThread("MOVIECAST_LIBTORRENT");
        libTorrentThread.start();
        libTorrentHandler = new Handler(libTorrentThread.getLooper());
        libTorrentHandler.post(new Runnable() {
            @Override
            public void run() {
                // initialize SettingsPack of LibTorrent

                torrentSession = new SessionManager();


                SettingsPack settingsPack = new SettingsPack()
                        .anonymousMode(false)
                        .connectionsLimit(200)
                        .downloadRateLimit(0)
                        .uploadRateLimit(0)
                        .activeDhtLimit(88);

                SessionParams sessionParams = new SessionParams(settingsPack);
                torrentSession.start(sessionParams);

                //torrentSession.startDht();
                torrentSession.addListener(new DHTStatsAlertListener(torrentSession) {
                    @Override
                    public void onStats(int totalNodes) {
                        Log.d("MOVIECAST_LIBTORRENT", "DHT Nodes: " + totalNodes);
                        //torrentSession.postDhtStats();

                        if(initLatch != null && totalNodes > 10) {
                            initLatch.countDown();
                        }
                    }
                });
                torrentSession.startDht();
                //torrentSession.postDhtStats();

                // add dth listener for information
                // start dth

                //initLatch.countDown();
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

                if(initLatch != null) {
                    try {
                        initLatch.await();
                        Log.d("MOVIECAST_STREAMER", "Initialized");
                        initLatch = null;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        streaming = false;
                    }
                }

                TorrentInfo info = TorrentUtils.getTorrentInfo(torrentSession, hash);

                Log.d("MOVIECAST_STREAMER", "Torrent Name: " + info.name());
                Log.d("MOVIECAST_STREAMER", "Torrent Amount Files: " + info.files().numFiles());

                // TODO: Write the actual logic...

                torrentSession.download(info, new File("/"));
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
