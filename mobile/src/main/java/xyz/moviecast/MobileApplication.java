/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast;

import xyz.moviecast.base.BaseApplication;
import xyz.moviecast.base.BaseApplicationModule;
import xyz.moviecast.streamer.Streamer;

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

        Streamer.getInstance().start("magnet:?xt=urn:btih:6268ABCCB049444BEE76813177AA46643A7ADA88");

        //TorrentInfo info = helper.getTorrentInfo("magnet:?xt=urn:btih:941D07549F35FFC8B7A3D033D50ABCFE137D976C");

        //Log.d("TORRENT", info.toString());
    }

    public MobileApplicationComponent getComponent() {
        return appComponent;
    }
}
