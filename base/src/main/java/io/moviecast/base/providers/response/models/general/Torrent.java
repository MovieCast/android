/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.base.providers.response.models.general;

import com.google.gson.annotations.SerializedName;

public class Torrent {

    @SerializedName("hash")
    private String hash;
    @SerializedName("seeds")
    private int seeds;
    @SerializedName("peers")
    private int peers;
    @SerializedName("size")
    private long size;
    @SerializedName("quality")
    private String quality;
    @SerializedName("fileSize")
    private String fileSize;
    @SerializedName("provider")
    private String provider;

    public Torrent() {
    }

    public String getHash() {
        return hash;
    }

    public int getSeeds() {
        return seeds;
    }

    public int getPeers() {
        return peers;
    }

    public long getSize() {
        return size;
    }

    public String getQuality() {
        return quality;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getProvider() {
        return provider;
    }
}
