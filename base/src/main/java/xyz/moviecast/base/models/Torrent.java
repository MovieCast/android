package xyz.moviecast.base.models;

public class Torrent {

    private String resolution;
    private String hash;
    private int seeds;
    private int peers;
    private int size;
    private String fileSize;
    private String provider;

    public Torrent(String resolution, String hash, int seeds, int peers, int size, String fileSize, String provider) {
        this.resolution = resolution;
        this.hash = hash;
        this.seeds = seeds;
        this.peers = peers;
        this.size = size;
        this.fileSize = fileSize;
        this.provider = provider;
    }

    public String getResolution() {
        return resolution;
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
