package xyz.moviecast.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.moviecast.R;

public class MovieFragment extends Fragment {

    private ViewPager viewPager;
    private View parent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parent = inflater.inflate(R.layout.fragment_movie, container, false);
        viewPager = parent.findViewById(R.id.viewPager);
        Adapter adapter = new Adapter(getFragmentManager());
        adapter.addFragment(new CatalogFragment(), "Trending");
        adapter.addFragment(new CatalogFragment(), "Year");
        adapter.addFragment(new CatalogFragment(), "A-Z");
        viewPager.setAdapter(adapter);

        addTabs();
//        addTabsOld();
        return parent;
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

    private void addTabsOld(){
        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                viewPager.setCurrentItem(tab.getPosition(), true);
            }

            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            }

            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            }
        };

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab().setText("Trending").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Year").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("A-Z").setTabListener(tabListener));

        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });
    }
}
