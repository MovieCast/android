/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package io.moviecast.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import io.moviecast.MobileApplication;
import io.moviecast.R;
import io.moviecast.adapters.MediaPagerAdapter;
import io.moviecast.base.app.BaseFragment;
import io.moviecast.base.managers.ProviderManager;
import io.moviecast.base.providers.MediaProvider;

public class MediaContainerFragment extends BaseFragment {

    public static final String KEY_TYPE = "TYPE";
    private static final String TAG = "MEDIA_CONTAINER";

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabs;

    @Inject
    ProviderManager providerManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState, R.layout.fragment_media_container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MobileApplication.getInstance()
                .getComponent()
                .inject(this);

        MediaProvider provider = providerManager.getCurrentProvider();

        viewPager.setAdapter(new MediaPagerAdapter(getChildFragmentManager(), provider.getTabs()));
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
