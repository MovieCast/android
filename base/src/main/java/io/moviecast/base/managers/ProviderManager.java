/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.base.managers;

import androidx.annotation.MainThread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.moviecast.base.BaseApplication;
import io.moviecast.base.R;
import io.moviecast.base.providers.MediaProvider;
import io.moviecast.base.providers.MovieProvider;
import io.moviecast.base.providers.ShowProvider;
import io.moviecast.base.utils.ThreadUtils;

public class ProviderManager {

    public enum ProviderType {MOVIES, SHOWS}

    private MediaProvider currentProvider;
    private final Map<ProviderType, MediaProvider> providers = new HashMap<>();
    private final ArrayList<ProviderListener> listeners = new ArrayList<>();

    ProviderManager(MovieProvider movieProvider, ShowProvider showProvider) {
        providers.put(ProviderType.MOVIES, movieProvider);
        providers.put(ProviderType.SHOWS, showProvider);

        setCurrentProvider(ProviderType.MOVIES);

    }

    /**
     * Get the title of the given provider.
     *
     * @param provider The provider
     * @return a translated provider title
     */
    public static String getProviderTitle(ProviderType provider) {
        switch (provider) {
            case MOVIES:
                return BaseApplication.getInstance().getString(R.string.title_movies);
            case SHOWS:
                return BaseApplication.getInstance().getString(R.string.title_shows);
            default:
                throw new IllegalStateException(provider + " is not a valid provider type");
        }
    }

    /**
     * @return the current selected provider
     */
    public MediaProvider getCurrentProvider() {
        return currentProvider;
    }

    /**
     * Set the current provider.
     *
     * Do note that this will also call {@link ProviderListener#onProviderChanged(ProviderType)}
     * @param provider The new provider
     */
    public void setCurrentProvider(ProviderType provider) {
        currentProvider = providers.get(provider);
        if(listeners.size() > 0) {
            for(ProviderListener listener : listeners) {
                ThreadUtils.runOnUiThread(() -> listener.onProviderChanged(provider));
            }
        }
    }

    /**
     * Add provider listener.
     * @param listener The listener to add
     */
    public void addProviderListener(ProviderListener listener) {
        listeners.add(listener);
    }

    /**
     * Remove provider listener.
     * @param listener The listener to remove
     */
    public void removeProviderListener(ProviderListener listener) {
        listeners.remove(listener);
    }

    public interface ProviderListener {
        /**
         * Called when the current provider changes.
         * @param provider The new provider type
         */
        @MainThread
        void onProviderChanged(ProviderType provider);
    }
}
