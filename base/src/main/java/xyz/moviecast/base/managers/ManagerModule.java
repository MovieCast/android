package xyz.moviecast.base.managers;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xyz.moviecast.base.providers.MovieProvider;

@Module
public class ManagerModule {

    @Provides
    @Singleton
    ProviderManager provideProviderManager(MovieProvider movieProvider) {
        return new ProviderManager(movieProvider);
    }
}
