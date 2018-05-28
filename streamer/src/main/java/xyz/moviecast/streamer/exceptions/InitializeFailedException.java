package xyz.moviecast.streamer.exceptions;

public class InitializeFailedException extends RuntimeException {

    private String reason;

    public InitializeFailedException(String reason) {
        super("MovieCast Streamer failed to initialize, reason: " + reason);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
