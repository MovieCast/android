package xyz.moviecast;

import javax.inject.Singleton;

import dagger.Component;
import xyz.moviecast.base.BaseApplicationComponent;
import xyz.moviecast.base.BaseApplicationModule;
import xyz.moviecast.fragments.MediaContainerFragment;

@Singleton
@Component(modules = BaseApplicationModule.class)
public interface MobileApplicationComponent extends BaseApplicationComponent {
    void inject(MediaContainerFragment fragment);
}
