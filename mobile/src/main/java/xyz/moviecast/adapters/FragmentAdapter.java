/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import xyz.moviecast.fragments.MediaCatalogFragment;
import xyz.moviecast.fragments.MediaContainerFragment;

public class FragmentAdapter extends FragmentPagerAdapter {

    private static final String TAG = "FRAGMENT_ADAPTER";

    private ArrayList<Fragment> fragments = new ArrayList<>();

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, int type, String sorting){
        Bundle bundle = new Bundle();
        bundle.putInt(MediaCatalogFragment.KEY_TYPE, type);
        bundle.putString(MediaCatalogFragment.KEY_SORTING, sorting);
        fragment.setArguments(bundle);
        fragments.add(fragment);
    }

    public void addFragment(Fragment fragment, int type){
        Bundle bundle = new Bundle();
        bundle.putInt(MediaContainerFragment.KEY_TYPE, type);
        fragment.setArguments(bundle);
        fragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: getItem(" + position + ") called");
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
