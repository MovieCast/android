/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.streamer.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class IOUtils {

    /**
     * Convert a {@link InputStream} into a <code>byte[]</code>
     *
     * @param is The InputStream to convert
     * @return a byte array of the given InputStream
     */
    public static byte[] toByteArray(InputStream is) throws IOException {
        byte[] buffer = new byte[(int) Math.min(Integer.MAX_VALUE, is.available() * 1.25)];
        ByteArrayOutputStream os = new ByteArrayOutputStream(buffer.length);

        int read;
        while ((read = is.read(buffer)) != -1) {
            os.write(buffer, 0, read);
        }

        // Not needed
        os.flush();

        return os.toByteArray();
    }
}
