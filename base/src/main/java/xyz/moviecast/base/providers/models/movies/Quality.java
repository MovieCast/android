package xyz.moviecast.base.providers.models.movies;

import org.codehaus.jackson.annotate.JsonProperty;

public class Quality {

    @JsonProperty("hash")
    private String hash;
    @JsonProperty("seeds")
    private int seeds;
    @JsonProperty("peers")
    private int peers;
    @JsonProperty("size")
    private int size;
    @JsonProperty("fileSize")
    private String fileSize;
    @JsonProperty("provider")
    private String provider;

    public Quality() {

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

    public int getSize() {
        return size;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getProvider() {
        return provider;
    }
}
