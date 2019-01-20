/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;
import java.util.Locale;

import io.moviecast.base.providers.MediaProvider;
import io.moviecast.fragments.MediaListFragment;

public class MediaPagerAdapter extends FragmentStatePagerAdapter {

    private final List<MediaProvider.Tab> tabs;

    public MediaPagerAdapter(FragmentManager fm, List<MediaProvider.Tab> tabs) {
        super(fm);
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
