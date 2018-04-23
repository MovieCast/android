package xyz.moviecast.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import xyz.moviecast.MobileApplication;
import xyz.moviecast.R;
import xyz.moviecast.adapters.FragmentAdapter;
import xyz.moviecast.adapters.MediaPagerAdapter;
import xyz.moviecast.base.Constants;
import xyz.moviecast.base.managers.ProviderManager;
import xyz.moviecast.base.providers.MediaProvider;
import xyz.moviecast.base.providers.models.movies.Movie;

public class MediaContainerFragment extends Fragment {

    public static final String KEY_TYPE = "TYPE";
    private static final String TAG = "MEDIA_CONTAINER";

    private ViewPager viewPager;
    private TabLayout tabs;
    private View parent;

    @Inject
    ProviderManager providerManager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //updateTabs();
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
