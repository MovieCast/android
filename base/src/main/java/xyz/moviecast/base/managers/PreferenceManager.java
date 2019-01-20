/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */


package xyz.moviecast.base.managers;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.StringRes;

public class PreferenceManager {

    private Context context;

    PreferenceManager(Context context) {
        this.context = context;
    }

    /**
     * @return The global {@link SharedPreferences}
     */
    public SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences("MCPreferences", Context.MODE_PRIVATE);
    }

    //region Save methods

    /**
     * Save a boolean to the global {@link SharedPreferences}
     * @param key   Key of the preference
     * @param value Value of the preference
     */
    public void save(@StringRes int key, boolean value) {
        save(context.getString(key), value);
    }

    /**
     * Save a boolean to the global {@link SharedPreferences}
     * @param key   Key of the preference
     * @param value Value of the preference
     */
    public void save(String key, boolean value) {
        getSharedPreferences().edit().putBoolean(key, value).apply();
    }

    /**
     * Save a string to the global {@link SharedPreferences}
     * @param key   Key of the preference
     * @param value Value of the preference
     */
    public void save(@StringRes int key, String value) {
        save(context.getString(key), value);
    }

    /**
     * Save a string to the global {@link SharedPreferences}
     * @param key   Key of the preference
     * @param value Value of the preference
     */
    public void save(String key, String value) {
        getSharedPreferences().edit().putString(key, value).apply();
    }

    /**
     * Save a float to the global {@link SharedPreferences}
     * @param key   Key of the preference
     * @param value Value of the preference
     */
    public void save(@StringRes int key, float value) {
        save(context.getString(key), value);
    }

    /**
     * Save a float to the global {@link SharedPreferences}
     * @param key   Key of the preference
     * @param value Value of the preference
     */
    public void save(String key, float value) {
        getSharedPreferences().edit().putFloat(key, value).apply();
    }

    /**
     * Save a int to the global {@link SharedPreferences}
     * @param key   Key of the preference
     * @param value Value of the preference
     */
    public void save(@StringRes int key, int value) {
        save(context.getString(key), value);
    }

    /**
     * Save a int to the global {@link SharedPreferences}
     * @param key   Key of the preference
     * @param value Value of the preference
     */
    public void save(String key, int value) {
        getSharedPreferences().edit().putInt(key, value).apply();
    }

    /**
     * Save a long to the global {@link SharedPreferences}
     * @param key   Key of the preference
     * @param value Value of the preference
     */
    public void save(@StringRes int key, long value) {
        save(context.getString(key), value);
    }

    /**
     * Save a long to the global {@link SharedPreferences}
     * @param key   Key of the preference
     * @param value Value of the preference
     */
    public void save(String key, long value) {
        getSharedPreferences().edit().putLong(key, value).apply();
    }

    //endregion

    //region Get methods

    /**
     * Get a saved boolean from the global {@link SharedPreferences}
     * @param key           Key of the preference
     * @param defaultValue  Default fallback value
     * @return The saved boolean
     */
    public boolean get(@StringRes int key, boolean defaultValue) {
        return get(context.getString(key), defaultValue);
    }

    /**
     * Get a saved boolean from the global {@link SharedPreferences}
     * @param key           Key of the preference
     * @param defaultValue  Default fallback value
     * @return The saved boolean
     */
    public boolean get(String key, boolean defaultValue) {
        return getSharedPreferences().getBoolean(key, defaultValue);
    }

    /**
     * Get a saved string from the global {@link SharedPreferences}
     * @param key           Key of the preference
     * @param defaultValue  Default fallback value
     * @return The saved string
     */
    public String get(@StringRes int key, String defaultValue) {
        return get(context.getString(key), defaultValue);
    }

    /**
     * Get a saved string from the global {@link SharedPreferences}
     * @param key           Key of the preference
     * @param defaultValue  Default fallback value
     * @return The saved string
     */
    public String get(String key, String defaultValue) {
        return getSharedPreferences().getString(key, defaultValue);
    }

    /**
     * Get a saved float from the global {@link SharedPreferences}
     * @param key           Key of the preference
     * @param defaultValue  Default fallback value
     * @return The saved float
     */
    public float get(@StringRes int key, float defaultValue) {
        return get(context.getString(key), defaultValue);
    }

    /**
     * Get a saved float from the global {@link SharedPreferences}
     * @param key           Key of the preference
     * @param defaultValue  Default fallback value
     * @return The saved float
     */
    public float get(String key, float defaultValue) {
        return getSharedPreferences().getFloat(key, defaultValue);
    }

    /**
     * Get a saved int from the global {@link SharedPreferences}
     * @param key           Key of the preference
     * @param defaultValue  Default fallback value
     * @return The saved int
     */
    public int get(@StringRes int key, int defaultValue) {
        return get(context.getString(key), defaultValue);
    }

    /**
     * Get a saved int from the global {@link SharedPreferences}
     * @param key           Key of the preference
     * @param defaultValue  Default fallback value
     * @return The saved int
     */
    public int get(String key, int defaultValue) {
        return getSharedPreferences().getInt(key, defaultValue);
    }

    /**
     * Get a saved long from the global {@link SharedPreferences}
     * @param key           Key of the preference
     * @param defaultValue  Default fallback value
     * @return The saved long
     */
    public long get(@StringRes int key, long defaultValue) {
        return get(context.getString(key), defaultValue);
    }

    /**
     * Get a saved long from the global {@link SharedPreferences}
     * @param key           Key of the preference
     * @param defaultValue  Default fallback value
     * @return The saved long
     */
    public long get(String key, long defaultValue) {
        return getSharedPreferences().getLong(key, defaultValue);
    }

    //endregion

    /**
     * Check if the global {@link SharedPreferences} contains a preference on a given key
     * @param key   Key of the preference
     * @return {@code true} if given key exists
     */
    public boolean contains(@StringRes int key) {
        return contains(context.getString(key));
    }

    /**
     * Check if the global {@link SharedPreferences} contains a preference on a given key
     * @param key   Key of the preference
     * @return {@code true} if given key exists
     */
    public boolean contains(String key) {
        return getSharedPreferences().contains(key);
    }

    /**
     * Remove a preference from the global {@link SharedPreferences}
     * @param key   Key of the preference
     */
    public void remove(@StringRes int key) {
        remove(context.getString(key));
    }

    /**
     * Remove a preference from the global {@link SharedPreferences}
     * @param key   Key of the preference
     */
    public void remove(String key) {
        getSharedPreferences().edit().remove(key).apply();
    }

    /**
     * Clear the global {@link SharedPreferences}
     */
    public void clear() {
        getSharedPreferences().edit().clear().apply();
    }
}
