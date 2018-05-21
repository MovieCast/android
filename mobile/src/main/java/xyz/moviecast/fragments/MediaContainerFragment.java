/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import xyz.moviecast.MobileApplication;
import xyz.moviecast.R;
import xyz.moviecast.adapters.MediaPagerAdapter;
import xyz.moviecast.base.managers.ProviderManager;
import xyz.moviecast.base.providers.MediaProvider;

public class MediaContainerFragment extends Fragment {

    public static final String KEY_TYPE = "TYPE";
    private static final String TAG = "MEDIA_CONTAINER";

    private ViewPager viewPager;
    private TabLayout tabs;
    private View parent;

    @Inject
    ProviderManager providerManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return parent = inflater.inflate(R.layout.fragment_media_container, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MobileApplication.getInstance()
                .getComponent()
                .inject(this);

        viewPager = parent.findViewById(R.id.viewPager);
        tabs = parent.findViewById(R.id.tabLayout);

        MediaProvider provider = providerManager.getCurrentProvider();

        viewPager.setAdapter(new MediaPagerAdapter(getChildFragmentManager(), provider, provider.getTabs()));
        updateTabs();
    }

    public void updateTabs() {
        Log.d(TAG, "updateTabs(): called");
        tabs.setupWithViewPager(viewPager, false);

        tabs.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);

        if(tabs.getTabCount() > 0) {
            //tabs.getTabAt(0).select();
        }
    }
}
