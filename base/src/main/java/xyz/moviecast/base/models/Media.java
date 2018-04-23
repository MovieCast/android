package xyz.moviecast.base.models;

import xyz.moviecast.base.managers.ProviderManager;

public abstract class Media {
    private String id;
    private String title;
    private String year;
    private Rating rating;
    private String posterImageUrl;
    private String backgroundImageUrl;

    Media(String id, String title, String year, Rating rating,
          String posterImageUrl, String backgroundImageUrl) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.posterImageUrl = posterImageUrl;
        this.backgroundImageUrl = backgroundImageUrl;
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

    public abstract ProviderManager.ProviderType getProviderType();
}
