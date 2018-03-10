package xyz.moviecast.base.providers.models.movies;

public class Torrent {

    private String hash;
    private int seeds;
    private int peers;
    private int size;
    private String quality;
    private String fileSize;
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

    public int getSize() {
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
