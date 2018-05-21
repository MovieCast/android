/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast;

import xyz.moviecast.base.BaseApplication;
import xyz.moviecast.base.BaseApplicationModule;

public class MobileApplication extends BaseApplication {

    private MobileApplicationComponent appComponent;

    public static MobileApplication getInstance() {
        return (MobileApplication) BaseApplication.getInstance();
    }

    @Override
    public void onCreate() {
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
