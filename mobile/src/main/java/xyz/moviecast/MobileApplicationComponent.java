/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast;

import javax.inject.Singleton;

import dagger.Component;
import xyz.moviecast.activities.MainActivity;
import xyz.moviecast.base.BaseApplicationComponent;
import xyz.moviecast.base.BaseApplicationModule;
import xyz.moviecast.fragments.MediaContainerFragment;
import xyz.moviecast.fragments.MediaListFragment;

@Singleton
@Component(modules = BaseApplicationModule.class)
public interface MobileApplicationComponent extends BaseApplicationComponent {
    void inject(MainActivity activity);

    void inject(MediaListFragment fragment);

    void inject(MediaContainerFragment fragment);
}
