/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

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
