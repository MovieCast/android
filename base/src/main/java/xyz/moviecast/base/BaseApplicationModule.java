package xyz.moviecast.base;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xyz.moviecast.base.data.DataModule;
import xyz.moviecast.base.managers.ManagerModule;
import xyz.moviecast.base.managers.ProviderManager;
import xyz.moviecast.base.providers.ProviderModule;

@Module(
        includes = {
                DataModule.class,
                ProviderModule.class,
                ManagerModule.class
        }
)
public class BaseApplicationModule {

    private BaseApplication app;

    public BaseApplicationModule(BaseApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return app.getApplicationContext();
    }
}
