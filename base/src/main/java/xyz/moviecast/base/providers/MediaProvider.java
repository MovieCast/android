package xyz.moviecast.base.providers;

import android.accounts.NetworkErrorException;
import android.support.annotation.NonNull;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import xyz.moviecast.base.providers.models.movies.Movie;

public abstract class MediaProvider<T> extends BaseProvider {

    private static final String TAG = "MEDIA_PROVIDER";

    private String baseUrl;
    private String listPath;
    private String detailPath;

    private Map<String, Movie> itemMap = new HashMap<>();

    public MediaProvider(OkHttpClient client, ObjectMapper mapper, String baseUrl, String listPath, String detailPath) {
        super(client, mapper);

        this.baseUrl = baseUrl;
        this.listPath = listPath;
        this.detailPath = detailPath;
    }

    public void providePage(final Filters filters, final MediaCallback callback) {
        HttpUrl.Builder httpBuilder = HttpUrl.parse(baseUrl+listPath+filters.getPage()).newBuilder();

        if(filters != null) {
            for(Map.Entry<String, String> param : filters.getQueryParams()) {
                httpBuilder.addQueryParameter(param.getKey(), param.getValue());
            }
        }

        Request.Builder responseBuilder = new Request.Builder().url(httpBuilder.build());

        Log.d(TAG, "Making request to url: " + httpBuilder.build().toString());

        enqueue(responseBuilder.build(), new Callback() {
            @Override
            public void onFailure(@NonNull Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    String rawResponse = response.body().string();

                    if(rawResponse.equals("")) {
                        callback.onFailure(new NetworkErrorException("API gave an empty response"));
                        return;
                    }

                    List<Movie> items = formatList(rawResponse);
                    for(Movie item : items) {
                        itemMap.put(item.getId(), item);
                    }

                    callback.onSuccess(filters, items);
                    return;
                }
                callback.onFailure(new NetworkErrorException("Unknown API error occurred"));
            }
        });
    }

    //public void provideDetails()

    abstract List<Movie> formatList(String response);

    //public abstract Call getTotalAmountOfMedia(Callback callback);
    //public abstract Call providePage(int page, String sorting, Callback callback);
    //public abstract T provideDetails(T object);

    public interface MediaCallback {
        void onSuccess(Filters filters, List<Movie> items);
        void onFailure(Exception e);
    }

    public static class Filters {
        Map<String, String> filters = new HashMap<>();

        int page = 1;

        public Filters() {
        }

        public String getKeywords() {
            return filters.get("keywords");
        }

        public void setKeywords(String keywords) {
            filters.put("keywords", keywords);
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        Set<Map.Entry<String, String>> getQueryParams() {
            return filters.entrySet();
        }
    }
}
