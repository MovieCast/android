/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.base.providers;

import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

class BaseProvider {

    private final OkHttpClient client;
    protected final Gson gson;

    BaseProvider(OkHttpClient client, Gson gson) {
        this.client = client;
        this.gson = gson;
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
