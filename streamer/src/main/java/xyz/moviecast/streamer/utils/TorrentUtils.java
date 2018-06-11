/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.streamer.utils;

import android.net.Uri;
import android.util.Log;

import com.frostwire.jlibtorrent.SessionManager;
import com.frostwire.jlibtorrent.TorrentInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public final class TorrentUtils {

    public static TorrentInfo getTorrentInfo(SessionManager torrentSession, String torrentUri) {
        return getTorrentInfo(torrentSession, Uri.parse(torrentUri));
    }

    public static TorrentInfo getTorrentInfo(SessionManager torrentSession, Uri torrentUri) {
        Log.d("MOVIECAST_TORRENT", "Scheme: " + torrentUri.getScheme());
        Log.d("MOVIECAST_TORRENT", "Path: " + torrentUri.toString());

        switch (torrentUri.getScheme()) {
            case "magnet":
                byte[] data = torrentSession.fetchMagnet(torrentUri.toString(), 10);
                Log.d("MOVIECAST_TORRENT", "Magnet Data Length: " + data.length);
                if(data != null && data.length > 0) {
                    return TorrentInfo.bdecode(data);
                }
                break;
            case "file":
                return getTorrentInfo(new File(torrentUri.getPath()));
            case "http":
            case "https":
                throw new RuntimeException("torrent file downloads are currently not supported!");
        }

        return null;
    }

    public static TorrentInfo getTorrentInfo(File torrentFile) {
        try {
            FileInputStream is = new FileInputStream(torrentFile);
            byte[] data = IOUtils.toByteArray(is);
            is.close();

            if(data.length > 0) {
                return TorrentInfo.bdecode(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
