/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.base.utils;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;

public final class ImageUtils {

    /**
     * Loads an url and sets the post processor
     * @param view The drawee view
     * @param url The image uri
     * @param postprocessor The post processor
     */
    public static void loadAndProcess(SimpleDraweeView view, String url, Postprocessor postprocessor) {
        loadAndProcess(view, Uri.parse(url), postprocessor);
    }

    /**
     * Loads an uri and sets the post processor
     * @param view The drawee view
     * @param uri The image uri
     * @param postprocessor The post processor
     */
    public static void loadAndProcess(SimpleDraweeView view, Uri uri, Postprocessor postprocessor) {
        final ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setPostprocessor(postprocessor)
                .build();

        final DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setOldController(view.getController())
                .setImageRequest(imageRequest)
                .build();

        view.setController(draweeController);
    }

}
