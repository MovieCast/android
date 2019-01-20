/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.base.providers;

import android.accounts.NetworkErrorException;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import io.moviecast.base.BaseApplication;
import io.moviecast.base.models.Media;

public abstract class MediaProvider extends BaseProvider {

    private static final String TAG = "MEDIA_PROVIDER";

    private final String baseUrl;
    private final String listPath;
    private final String detailPath;

    private final Map<String, Media> itemCache = new HashMap<>();

    MediaProvider(OkHttpClient client, Gson gson, String baseUrl, String listPath, String detailPath) {
        super(client, gson);

        this.baseUrl = baseUrl;
        this.listPath = listPath;
        this.detailPath = detailPath;
    }

    /**
     * Get a media object by it's id
     * @param id The media id
     */
    public Media getMediaById(String id) {
        if(!itemCache.containsKey(id)) return null;

        return itemCache.get(id);
    }

    public void providePage(final MediaListCallback callback) {
        providePage(new Filters(), callback);
    }

    public void providePage(final Filters filters, final MediaListCallback callback) {
        HttpUrl.Builder httpBuilder = HttpUrl.parse(baseUrl).newBuilder();

        httpBuilder.addPathSegment(listPath);
        httpBuilder.addPathSegment(String.valueOf(filters.getPage()));

        for(Map.Entry<String, String> param : filters.getQueryParams()) {
            httpBuilder.addQueryParameter(param.getKey(), param.getValue());
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

                    Map<String, Media> formattedItems = formatList(rawResponse);

                    // Cache items
                    itemCache.putAll(formattedItems);

                    // Send list with item id's
                    callback.onSuccess(filters, new LinkedHashSet<>(formattedItems.values()));
                    return;
                }
                callback.onFailure(new NetworkErrorException("Unknown API error occurred"));
            }
        });
    }

    public void provideDetails(final String id, final MediaDetailCallback callback) {
        if(itemCache.containsKey(id) && itemCache.get(id).isDetailLoaded()) {
            callback.onSuccess(itemCache.get(id));
            return;
        }

        HttpUrl.Builder httpBuilder = HttpUrl.parse(baseUrl).newBuilder();
        httpBuilder.addPathSegment(listPath);
        httpBuilder.addPathSegment(detailPath);
        httpBuilder.addPathSegment(id);

        Log.d(TAG, "Making request to url: " + httpBuilder.build().toString());
        Request.Builder requestBuilder = new Request.Builder().url(httpBuilder.build());

        enqueue(requestBuilder.build(), new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    String rawResponse = response.body().string();

                    if (rawResponse.equals("")) {
                        callback.onFailure(new NetworkErrorException("API gave an empty response"));
                        return;
                    }

                    Media formattedItem = formatDetail(rawResponse);
                    formattedItem.setDetailLoaded(true);

                    // TODO: Check whether this is needed...
                    itemCache.put(id, formattedItem);

                    callback.onSuccess(formattedItem);
                    return;
                }
                callback.onFailure(new NetworkErrorException("Unknown API error occurred"));
            }
        });
    }

    abstract Map<String, Media> formatList(String response) throws IOException;

    abstract Media formatDetail(String response) throws IOException;

    public abstract List<Tab> getTabs();

    public interface MediaListCallback {
        void onSuccess(Filters filters, Set<Media> result);
        void onFailure(Exception e);
    }

    public interface MediaDetailCallback {
        void onSuccess(Media result);
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
