/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.base.providers;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import xyz.moviecast.base.R;
import xyz.moviecast.base.models.Media;
import xyz.moviecast.base.providers.response.ShowDetailResponse;
import xyz.moviecast.base.providers.response.ShowListResponse;

public class ShowProvider extends MediaProvider {
    ShowProvider(OkHttpClient client, Gson gson) {
        super(client, gson, "https://content.moviecast.io", "shows", "detail");
    }

    @Override
    Map<String, Media> formatList(String response) throws IOException {
        Map<String, Media> formattedItems = new LinkedHashMap<>();

        ShowListResponse page = gson.fromJson(response, ShowListResponse.class);
        for(Media item : page.getFormattedResult()) {
            formattedItems.put(item.getId(), item);
        }

        return formattedItems;
    }

    @Override
    Media formatDetail(String response) throws IOException {
        ShowDetailResponse detailResponse = gson.fromJson(response, ShowDetailResponse.class);
        return detailResponse.getFormattedItem();
    }

    @Override
    public List<Tab> getTabs() {
        List<Tab> tabs = new ArrayList<>();

        tabs.add(new Tab(R.id.movie_filter_trending, R.string.trending, Filters.Sort.TRENDING, Filters.Order.DESC));
        tabs.add(new Tab(R.id.movie_filter_popular, R.string.popular, Filters.Sort.POPULARITY, Filters.Order.DESC));
        tabs.add(new Tab(R.id.movie_filter_rating, R.string.rating, Filters.Sort.RATING, Filters.Order.DESC));
        tabs.add(new Tab(R.id.movie_filter_released, R.string.released, Filters.Sort.RELEASED, Filters.Order.DESC));
        tabs.add(new Tab(R.id.movie_filter_year, R.string.year, Filters.Sort.YEAR, Filters.Order.DESC));
        tabs.add(new Tab(R.id.movie_filter_alphabetic, R.string.alphabet, Filters.Sort.ALPHABET, Filters.Order.DESC));

        return tabs;
    }
}
