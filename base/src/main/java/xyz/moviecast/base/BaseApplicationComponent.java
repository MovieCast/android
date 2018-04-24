package xyz.moviecast.base;

import android.app.Application;
import android.content.Context;

import okhttp3.OkHttpClient;
import xyz.moviecast.base.managers.ProviderManager;

public interface BaseApplicationComponent {

    Application application();

    Context context();

    OkHttpClient okHttpClient();

    ProviderManager providerManager();



}
