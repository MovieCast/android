package xyz.moviecast.base.providers;

import android.content.Context;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

import xyz.moviecast.base.providers.BaseProvider;
import xyz.moviecast.base.providers.models.movies.Page;

public abstract class MediaProvider<T> extends BaseProvider {

    static final ObjectMapper mapper = new ObjectMapper();

    MediaProvider(Context context) {
        super(context);
    }

    public abstract int getTotalAmountOfMedia() throws IOException;
    public abstract Page providePage(int page) throws IOException;
    public abstract T provideDetails(T object) throws IOException;
}
