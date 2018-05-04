package xyz.moviecast.base.providers;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class ProviderModule {

    @Provides
    @Singleton
    MovieProvider provideMovieProvider(OkHttpClient client, ObjectMapper mapper) {
        return new MovieProvider(client, mapper);
    }

    @Provides
    @Singleton
    ShowProvider provideShowProvider(OkHttpClient client, ObjectMapper mapper) {
        return new ShowProvider(client, mapper);
    }
}
