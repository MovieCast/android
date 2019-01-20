/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;

import java.util.HashSet;
import java.util.Set;

import xyz.moviecast.base.BaseApplication;
import xyz.moviecast.base.BaseApplicationModule;

public class MobileApplication extends BaseApplication {

    private MobileApplicationComponent appComponent;

    public static MobileApplication getInstance() {
        return (MobileApplication) BaseApplication.getInstance();
    }

    @Override
    public void onCreate() {
        //Set<RequestListener> requestListeners = new HashSet<>();
        //requestListeners.add(new RequestLoggingListener());
        //ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
        //        // other setters
        //        .setRequestListeners(requestListeners)
        //        .build();
        Fresco.initialize(this);
        //FLog.setMinimumLoggingLevel(FLog.VERBOSE);
        if(appComponent == null) {
            appComponent = DaggerMobileApplicationComponent.builder()
                    .baseApplicationModule(new BaseApplicationModule(this))
                    .build();
        }

        super.onCreate();
    }

    public MobileApplicationComponent getComponent() {
        return appComponent;
    }
}
