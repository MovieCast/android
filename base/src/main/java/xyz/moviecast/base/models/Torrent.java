package xyz.moviecast.base.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Torrent implements Parcelable {

    public static final Parcelable.Creator<Torrent> CREATOR = new Parcelable.Creator<Torrent>() {
        @Override
        public Torrent createFromParcel(Parcel in) {
            return new Torrent(in);
        }

        @Override
        public Torrent[] newArray(int size) {
            return new Torrent[size];
        }
    };

    private String resolution;
    private String hash;
    private int seeds;
    private int peers;
    private long size;

    public Torrent(String resolution, String hash, int seeds, int peers, long size) {
        this.resolution = resolution;
        this.hash = hash;
        this.seeds = seeds;
        this.peers = peers;
        this.size = size;
    }

    private Torrent(Parcel in) {
        resolution = in.readString();
        hash = in.readString();
        seeds = in.readInt();
        peers = in.readInt();
        size = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(resolution);
        dest.writeString(hash);
        dest.writeInt(seeds);
        dest.writeInt(peers);
        dest.writeLong(size);
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getSeeds() {
        return seeds;
    }

    public void setSeeds(int seeds) {
        this.seeds = seeds;
    }

    public int getPeers() {
        return peers;
    }

    public void setPeers(int peers) {
        this.peers = peers;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
