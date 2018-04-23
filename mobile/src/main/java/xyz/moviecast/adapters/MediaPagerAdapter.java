package xyz.moviecast.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.Locale;

import xyz.moviecast.base.providers.MediaProvider;
import xyz.moviecast.fragments.MediaListFragment;

public class MediaPagerAdapter extends FragmentStatePagerAdapter {

    private MediaProvider provider;

    public MediaPagerAdapter(FragmentManager fm, MediaProvider provider) {
        super(fm);
        this.provider = provider;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return ((MediaProvider.Tab) provider.getTabs().get(position)).getLabel().toUpperCase(Locale.getDefault());
    }

    @Override
    public Fragment getItem(int position) {
        MediaProvider.Tab currentTab = (MediaProvider.Tab) provider.getTabs().get(position);

        return MediaListFragment.newInstance(MediaListFragment.Mode.NORMAL, currentTab.getSort(), currentTab.getOrder());
    }

    @Override
    public int getCount() {
        return provider.getTabs().size();
    }
}
