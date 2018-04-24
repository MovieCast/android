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
