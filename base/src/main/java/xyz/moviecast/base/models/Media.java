package xyz.moviecast.base.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import xyz.moviecast.base.managers.ProviderManager;

public abstract class Media implements Parcelable {

    private String id;
    private String title;
    private String year;
    private Rating rating;
    private List<String> genres = new ArrayList<>();
    private String posterImageUrl;
    private String backgroundImageUrl;

    Media(String id, String title, String year, Rating rating,
          String posterImageUrl, String backgroundImageUrl, List<String> genres) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.posterImageUrl = posterImageUrl;
        this.backgroundImageUrl = backgroundImageUrl;
        this.genres = genres;
    }

    Media(Parcel in) {
        id = in.readString();
        title = in.readString();
        year = in.readString();
        rating = in.readParcelable(Rating.class.getClassLoader());
        posterImageUrl = in.readString();
        backgroundImageUrl = in.readString();

        int genreSize = in.readInt();
        for(int i = 0; i < genreSize; i++) {
            genres.add(in.readString());
        }
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public Rating getRating() {
        return rating;
    }

    public String getPosterImageUrl() {
        return posterImageUrl;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public abstract ProviderManager.ProviderType getProviderType();

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(year);
        dest.writeParcelable(rating, flags);

        dest.writeString(posterImageUrl);
        dest.writeString(backgroundImageUrl);

        dest.writeInt(genres.size());
        for(int i = 0; i < genres.size(); i++) {
            dest.writeString(genres.get(i));
        }
    }
}
