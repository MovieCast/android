/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast;

import javax.inject.Singleton;

import dagger.Component;
import io.moviecast.activities.MainActivity;
import io.moviecast.base.BaseApplicationComponent;
import io.moviecast.base.BaseApplicationModule;
import io.moviecast.fragments.MediaContainerFragment;
import io.moviecast.fragments.MediaListFragment;

@Singleton
@Component(modules = BaseApplicationModule.class)
public interface MobileApplicationComponent extends BaseApplicationComponent {
    void inject(MainActivity activity);

    void inject(MediaListFragment fragment);

    void inject(MediaContainerFragment fragment);
}
