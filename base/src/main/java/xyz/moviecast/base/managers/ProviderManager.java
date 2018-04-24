package xyz.moviecast.base.managers;

import android.support.annotation.MainThread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import xyz.moviecast.base.BaseApplication;
import xyz.moviecast.base.R;
import xyz.moviecast.base.providers.MediaProvider;
import xyz.moviecast.base.providers.MovieProvider;

public class ProviderManager {

    public enum ProviderType {MOVIES}

    private MediaProvider currentProvider;
    private final Map<ProviderType, MediaProvider> providers = new HashMap<>();
    private final ArrayList<ProviderListener> listeners = new ArrayList<>();

    ProviderManager(MovieProvider movieProvider) {
        providers.put(ProviderType.MOVIES, movieProvider);

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
     * Do note that this will also call @link {ProviderListener.onProviderChanged}
     * @param provider The new provider
     */
    public void setCurrentProvider(ProviderType provider) {
        currentProvider = providers.get(provider);
        if(listeners.size() > 0) {
            for(ProviderListener listener : listeners) {
                listener.onProviderChanged(provider);
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