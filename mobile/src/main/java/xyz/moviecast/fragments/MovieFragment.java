package xyz.moviecast.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        Adapter adapter = new Adapter(getFragmentManager());
        adapter.addFragment(new CatalogFragment(), "Trending");
        adapter.addFragment(new CatalogFragment(), "Year");
        adapter.addFragment(new CatalogFragment(), "A-Z");
        viewPager.setAdapter(adapter);

        addTabs();
        return view;
    }

    private void addTabs(){
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
