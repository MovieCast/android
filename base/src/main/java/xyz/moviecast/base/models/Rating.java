package xyz.moviecast.base.models;

public class Rating {

    private int votes;
    private int watching;
    private int percentage;

    public Rating(int votes, int watching, int percentage) {
        this.votes = votes;
        this.watching = watching;
        this.percentage = percentage;
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
}
