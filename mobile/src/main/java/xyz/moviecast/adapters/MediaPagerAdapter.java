package xyz.moviecast.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import xyz.moviecast.base.providers.MediaProvider;
import xyz.moviecast.fragments.MediaListFragment;

public class MediaPagerAdapter extends FragmentStatePagerAdapter {

    private MediaProvider provider;

    public MediaPagerAdapter(FragmentManager fm, MediaProvider provider) {
        super(fm);
        this.provider = provider;
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
