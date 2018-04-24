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
import xyz.moviecast.base.BaseApplication;
import xyz.moviecast.base.models.Media;
import xyz.moviecast.base.providers.models.movies.Movie;

public abstract class MediaProvider extends BaseProvider {

    private static final String TAG = "MEDIA_PROVIDER";

    private String baseUrl;
    private String listPath;
    private String detailPath;

    MediaProvider(OkHttpClient client, ObjectMapper mapper, String baseUrl, String listPath, String detailPath) {
        super(client, mapper);

        this.baseUrl = baseUrl;
        this.listPath = listPath;
        this.detailPath = detailPath;
    }

    public void providePage(final MediaCallback callback) {
        providePage(new Filters(), callback);
    }

    public void providePage(final Filters filters, final MediaCallback callback) {
        HttpUrl.Builder httpBuilder = HttpUrl.parse(baseUrl+listPath+filters.getPage()).newBuilder();

        //if(filters != null) {
            for(Map.Entry<String, String> param : filters.getQueryParams()) {
                httpBuilder.addQueryParameter(param.getKey(), param.getValue());
            }
        //}

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

                    List<Media> items = formatList(rawResponse);
                    callback.onSuccess(filters, items);
                    return;
                }
                callback.onFailure(new NetworkErrorException("Unknown API error occurred"));
            }
        });
    }

    //public void provideDetails()

    abstract List<Media> formatList(String response);

    public abstract List<Tab> getTabs();

    //public abstract Call getTotalAmountOfMedia(Callback callback);
    //public abstract Call providePage(int page, String sorting, Callback callback);
    //public abstract T provideDetails(T object);

    public interface MediaCallback {
        void onSuccess(Filters filters, List<Media> items);
        void onFailure(Exception e);
    }

    public static class Filters {
        Map<String, String> filters = new HashMap<>();

        int page = 1;

        public Filters() {
            setSort(Sort.TRENDING);
            setOrder(Order.DESC);
        }

        public void setKeywords(String keywords) {
            filters.put("keywords", keywords);
        }

        public void setSort(Sort sort) {
            filters.put("sort", sort.toString().toLowerCase());
        }

        public void setOrder(Order order) {
            if(order == Order.ASC) {
                filters.put("order", "1");
            } else {
                filters.put("order", "-1");
            }
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

        public enum Sort { TRENDING, POPULARITY, RATING, RELEASED, YEAR, ALPHABET }

        public enum Order { ASC, DESC }
    }

    public static class Tab {
        private int id;
        private String label;
        private Filters.Sort sort;
        private Filters.Order order;

        Tab(int id, int stringId, Filters.Sort sort, Filters.Order order) {
            this(id, BaseApplication.getInstance().getString(stringId), sort, order);
        }

        Tab(int id, String label, Filters.Sort sort, Filters.Order order) {
            this.id = id;
            this.label = label;
            this.sort = sort;
            this.order = order;
        }

        public int getId() {
            return id;
        }

        public String getLabel() {
            return label;
        }

        public Filters.Sort getSort() {
            return sort;
        }

        public Filters.Order getOrder() {
            return order;
        }
    }
}
