package xyz.moviecast.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

public class Adapter extends FragmentStatePagerAdapter {

    private static final String TAG = "FRAGMENT_ADAPTER";

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private int lastPosition = -1;

    public Adapter(FragmentManager fm) {
        super(fm);
    }

    public String getCurrentTitle(){
        if(lastPosition != -1)
            return names.get(lastPosition);
        return null;
    }

    public void addFragment(Fragment fragment, String title){
        fragments.add(fragment);
        names.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        lastPosition = position;
        Log.d(TAG, "getItem: getItem(" + position + ") called");
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
