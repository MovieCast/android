package xyz.moviecast.streamer;

import com.frostwire.jlibtorrent.SessionManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StreamerModule {

    @Provides
    @Singleton
    SessionManager provideTorrentSession() {
        return new SessionManager();
    }

    @Provides
    @Singleton
    Streamer provideStreamer(SessionManager torrentSession) {
        return new Streamer(torrentSession);
    }
}
