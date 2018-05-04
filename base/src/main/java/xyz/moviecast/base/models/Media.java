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
    //private Rating rating;
    private double rating;
    private List<String> genres = new ArrayList<>();
    private String posterImageUrl;
    private String backgroundImageUrl;

    private boolean detailLoaded = false;

    Media() {

    }

    Media(Parcel in) {
        id = in.readString();
        title = in.readString();
        year = in.readString();
        //rating = in.readParcelable(Rating.class.getClassLoader());
        rating = in.readDouble();
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

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPosterImageUrl() {
        return posterImageUrl;
    }

    public void setPosterImageUrl(String posterImageUrl) {
        this.posterImageUrl = posterImageUrl;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public void setBackgroundImageUrl(String backgroundImageUrl) {
        this.backgroundImageUrl = backgroundImageUrl;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public boolean isDetailLoaded() {
        return detailLoaded;
    }

    public void setDetailLoaded(boolean flag) {
        detailLoaded = flag;
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
        dest.writeDouble(rating);

        dest.writeString(posterImageUrl);
        dest.writeString(backgroundImageUrl);

        dest.writeInt(genres.size());
        for(int i = 0; i < genres.size(); i++) {
            dest.writeString(genres.get(i));
        }
    }


}
