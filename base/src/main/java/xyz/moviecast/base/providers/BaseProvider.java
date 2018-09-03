/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.base.providers;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

class BaseProvider {

    private final OkHttpClient client;
    protected final ObjectMapper mapper;

    BaseProvider(OkHttpClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    protected Call enqueue(Request request, Callback callback) {
        request = request.newBuilder().tag(getClass()).build();

        Call call = client.newCall(request);
        if(callback != null) {
            call.enqueue(callback);
        }

        return call;
    }
}
