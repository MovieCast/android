package xyz.moviecast.base.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import xyz.moviecast.base.managers.ProviderManager;

public class Show extends Media implements Serializable {

    public static final Creator<Show> CREATOR = new Creator<Show>() {
        @Override
        public Show createFromParcel(Parcel source) {
            return new Show(source);
        }

        @Override
        public Show[] newArray(int size) {
            return new Show[size];
        }
    };

    private String tvdbId;
    private String synopsis;
    private int duration;
    private String country;
    private String network;
    private String airDay;
    private String airTime;
    private String status;
    private int numSeasons;
    private List<Episode> episodes = new ArrayList<>();

    public Show() { }

    private Show(Parcel in) {
        super(in);
        tvdbId = in.readString();
        synopsis = in.readString();
        duration = in.readInt();
        country = in.readString();
        network = in.readString();
        airDay = in.readString();
        airTime = in.readString();
        status = in.readString();
        numSeasons = in.readInt();
        episodes = in.createTypedArrayList(Episode.CREATOR);
    }

    public String getTvdbId() {
        return tvdbId;
    }

    public void setTvdbId(String tvdbId) {
        this.tvdbId = tvdbId;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getAirDay() {
        return airDay;
    }

    public void setAirDay(String airDay) {
        this.airDay = airDay;
    }

    public String getAirTime() {
        return airTime;
    }

    public void setAirTime(String airTime) {
        this.airTime = airTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumSeasons() {
        return numSeasons;
    }

    public void setNumSeasons(int numSeasons) {
        this.numSeasons = numSeasons;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    @Override
    public ProviderManager.ProviderType getProviderType() {
        return ProviderManager.ProviderType.SHOWS;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(tvdbId);
        dest.writeString(synopsis);
        dest.writeInt(duration);
        dest.writeString(country);
        dest.writeString(network);
        dest.writeString(airDay);
        dest.writeString(airTime);
        dest.writeString(status);
        dest.writeInt(numSeasons);
        dest.writeTypedList(episodes);
    }

    public static class Episode implements Parcelable {

        public static final Creator<Episode> CREATOR = new Creator<Episode>() {
            @Override
            public Episode createFromParcel(Parcel in) {
                return new Episode(in);
            }

            @Override
            public Episode[] newArray(int size) {
                return new Episode[size];
            }
        };

        private String tvdbId;
        private int season;
        private int episode;
        private String title;
        private String overview;
        private List<Torrent> torrents = new ArrayList<>();

        public Episode() { }

        private Episode(Parcel in) {
            tvdbId = in.readString();
            season = in.readInt();
            episode = in.readInt();
            title = in.readString();
            overview = in.readString();
            torrents = in.createTypedArrayList(Torrent.CREATOR);
        }

        public String getTvdbId() {
            return tvdbId;
        }

        public void setTvdbId(String tvdbId) {
            this.tvdbId = tvdbId;
        }

        public int getSeason() {
            return season;
        }

        public void setSeason(int season) {
            this.season = season;
        }

        public int getEpisode() {
            return episode;
        }

        public void setEpisode(int episode) {
            this.episode = episode;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public List<Torrent> getTorrents() {
            return torrents;
        }

        public void setTorrents(List<Torrent> torrents) {
            this.torrents = torrents;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(tvdbId);
            dest.writeInt(season);
            dest.writeInt(episode);
            dest.writeString(title);
            dest.writeString(overview);
            dest.writeTypedList(torrents);
        }
    }
}
