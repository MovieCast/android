/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.base.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Rating implements Parcelable {

    public static final Creator<Rating> CREATOR = new Creator<Rating>() {
        @Override
        public Rating createFromParcel(Parcel in) {
            return new Rating(in);
        }

        @Override
        public Rating[] newArray(int size) {
            return new Rating[size];
        }
    };

    private int votes;
    private int watching;
    private int percentage;

    public Rating(int votes, int watching, int percentage) {
        this.votes = votes;
        this.watching = watching;
        this.percentage = percentage;
    }

    private Rating(Parcel in) {
        votes = in.readInt();
        watching = in.readInt();
        percentage = in.readInt();
    }

    public int getVotes() {
        return votes;
    }

    public int getWatching() {
        return watching;
    }

    public int getPercentage() {
        return percentage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(votes);
        dest.writeInt(watching);
        dest.writeInt(percentage);
    }
}
