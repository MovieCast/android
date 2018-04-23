package xyz.moviecast.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;

import xyz.moviecast.R;
import xyz.moviecast.base.Constants;
import xyz.moviecast.adapters.FragmentAdapter;
import xyz.moviecast.base.managers.ProviderManager;
import xyz.moviecast.fragments.MediaContainerFragment;
import xyz.moviecast.fragments.SettingsFragment;
import xyz.moviecast.views.NonSwipeableViewPager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAIN_ACTIVITY";

    private ListView listView;
    private DrawerLayout drawerLayout;
    private NonSwipeableViewPager viewPager;
    private ArrayAdapter<String> arrayAdapter;
    private ActionBarDrawerToggle drawerToggle;

    private static MainActivity instance;

    public static MainActivity getInstance(){
        return instance;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.navList);
        drawerLayout = findViewById(R.id.drawerLayout);
        //viewPager = findViewById(R.id.nonSwipeableViewPager);

        //addFragments();
        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //viewPager.setCurrentItem(0, true);
        //setTitle("");
        showProvider(ProviderManager.ProviderType.MOVIE);
    }

    private void addFragments(){
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(new MediaContainerFragment(), Constants.MOVIES);
        fragmentAdapter.addFragment(new SettingsFragment(), Constants.SETTINGS);
        viewPager.setAdapter(fragmentAdapter);
    }

    private void addDrawerItems(){
        String[] osArray = {"Movies", "Settings"};
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            //viewPager.setCurrentItem(i, true);
            drawerLayout.closeDrawers();
        });
    }

    private void setupDrawer(){
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                invalidateOptionsMenu();
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }
        };

        drawerToggle.setDrawerIndicatorEnabled(true);
        //drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void showProvider(ProviderManager.ProviderType provider) {
        setTitle(ProviderManager.getProviderTitle(provider));

        FragmentManager manager = getSupportFragmentManager();
        MediaContainerFragment containerFragment = new MediaContainerFragment();
        manager.beginTransaction().replace(R.id.container, containerFragment).commit();
    }
}
