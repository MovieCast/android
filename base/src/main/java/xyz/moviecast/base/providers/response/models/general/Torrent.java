/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.base.providers.response.models.general;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Torrent {

    @JsonProperty("hash")
    private String hash;
    @JsonProperty("seeds")
    private int seeds;
    @JsonProperty("peers")
    private int peers;
    @JsonProperty("size")
    private long size;
    @JsonProperty("quality")
    private String quality;
    @JsonProperty("fileSize")
    private String fileSize;
    @JsonProperty("provider")
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
