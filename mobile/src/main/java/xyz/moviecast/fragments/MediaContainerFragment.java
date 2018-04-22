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
    private ActionBar actionBar;
    private View parent;

    @Inject
    ProviderManager providerManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        int type = arguments.getInt("TYPE", Constants.MOVIES);

        parent = inflater.inflate(R.layout.fragment_media_container, container, false);
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        viewPager = parent.findViewById(R.id.viewPager);
//        FragmentAdapter fragmentAdapter = new FragmentAdapter(getFragmentManager());
//        fragmentAdapter.addFragment(new MediaCatalogFragment(), type, Constants.TRENDING);
//        fragmentAdapter.addFragment(new MediaCatalogFragment(), type, Constants.YEAR);
//        fragmentAdapter.addFragment(new MediaCatalogFragment(), type, Constants.ALPHABETICAL);


        switch (type){
            case Constants.MOVIES:
                actionBar.setTitle("Movies");
                break;
            case Constants.SERIES:
                actionBar.setTitle("Series");
                break;
            case Constants.ANIME:
                actionBar.setTitle("Anime");
                break;
        }

        addTabs();
        return parent;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MobileApplication.getInstance()
                .getComponent()
                .inject(this);

        MediaProvider.Filters filters = new MediaProvider.Filters();
        filters.setPage(1);

        viewPager.setAdapter(new MediaPagerAdapter(getFragmentManager(), providerManager.getCurrentProvider()));
    }

    private void addTabs(){
        TabLayout layout = parent.findViewById(R.id.tabLayout);
        layout.addTab(layout.newTab().setText("Trending"));
        layout.addTab(layout.newTab().setText("Year"));
        layout.addTab(layout.newTab().setText("A-Z"));
        layout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(layout));
        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
