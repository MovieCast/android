package xyz.moviecast.base.providers;

import java.io.IOException;

import okhttp3.OkHttpClient;

abstract class ContentProvider<T> {

    OkHttpClient httpClient;

    ContentProvider(){
        httpClient = new OkHttpClient();
    }

    public abstract T[] loadPage(int page) throws IOException;
    public abstract T loadContent(T content) throws IOException;

}
