package xyz.moviecast.base.models;

import android.os.Parcel;

import java.io.Serializable;

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

    public Show() {

    }

    private Show(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @Override
    public ProviderManager.ProviderType getProviderType() {
        return ProviderManager.ProviderType.SHOWS;
    }
}
