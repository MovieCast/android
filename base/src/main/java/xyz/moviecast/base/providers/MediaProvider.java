package xyz.moviecast.base.providers;

import android.content.Context;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import xyz.moviecast.base.providers.BaseProvider;
import xyz.moviecast.base.providers.models.movies.Page;

public abstract class MediaProvider<T> extends BaseProvider {

    static final ObjectMapper mapper = new ObjectMapper();

    public abstract Call getTotalAmountOfMedia(Callback callback);
    public abstract Call providePage(int page, String sorting, Callback callback);
    public abstract T provideDetails(T object);
}
