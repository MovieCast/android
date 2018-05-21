/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;
import java.util.Locale;

import xyz.moviecast.base.providers.MediaProvider;
import xyz.moviecast.fragments.MediaListFragment;

public class MediaPagerAdapter extends FragmentStatePagerAdapter {

    private MediaProvider provider;
    private final List<MediaProvider.Tab> tabs;

    public MediaPagerAdapter(FragmentManager fm, MediaProvider provider, List<MediaProvider.Tab> tabs) {
        super(fm);
        this.provider = provider;
        this.tabs = tabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getLabel().toUpperCase(Locale.getDefault());
    }

    @Override
    public Fragment getItem(int position) {
        MediaProvider.Tab currentTab = tabs.get(position);
        return MediaListFragment.newInstance(MediaListFragment.Mode.NORMAL, currentTab.getSort(), currentTab.getOrder());
    }

    @Override
    public int getCount() {
        return tabs.size();
    }
}
