package xyz.moviecast.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import javax.inject.Inject;

import xyz.moviecast.MobileApplication;
import xyz.moviecast.R;
import xyz.moviecast.adapters.DrawerAdapter;
import xyz.moviecast.base.managers.ProviderManager;
import xyz.moviecast.fragments.MediaContainerFragment;

public class MainActivity extends AppCompatActivity implements ProviderManager.ProviderListener {

    private static final String TAG = "MAIN_ACTIVITY";

    @Inject
    ProviderManager providerManager;

    private ListView listView;
    private DrawerLayout drawerLayout;
    private ArrayAdapter<String> arrayAdapter;
    private ActionBarDrawerToggle drawerToggle;

    private static MainActivity instance;

    public static MainActivity getInstance(){
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MobileApplication.getInstance()
                .getComponent()
                .inject(this);

        setContentView(R.layout.activity_main);
        instance = this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.navList);
        drawerLayout = findViewById(R.id.drawerLayout);

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        showProvider(ProviderManager.ProviderType.MOVIES);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MainActivity.onResume(): MainActivity has been resumed, adding provider listener.");
        providerManager.addProviderListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity.onPause(): MainActivity has been paused, removing provider listener.");
        providerManager.removeProviderListener(this);
    }

    private void addDrawerItems() {
        //String[] osArray = {"Movies", "Shows", "Settings"};
        //arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);

        ArrayList<DrawerAdapter.DrawerItem> drawerItems = new ArrayList<>();
        drawerItems.add(new DrawerAdapter.DrawerItem.ProviderDrawerItem(R.drawable.ic_nav_movies, R.string.title_movies, ProviderManager.ProviderType.MOVIES));
        drawerItems.add(new DrawerAdapter.DrawerItem.IntentDrawerItem(R.drawable.ic_nav_settings, R.string.title_settings, SettingsActivity.getIntent(this)));

        DrawerAdapter adapter = new DrawerAdapter(this, drawerItems);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            DrawerAdapter.DrawerItem item = adapter.getItem(i);
            switch (item.getType()) {
                case INTENT:
                    startActivity(((DrawerAdapter.DrawerItem.IntentDrawerItem) item).getIntent());
                    break;
                case PROVIDER:
                    providerManager.setCurrentProvider(((DrawerAdapter.DrawerItem.ProviderDrawerItem) item).getProvider());
                    break;
            }
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
        drawerLayout.setDrawerListener(drawerToggle);
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

    @Override
    public void onProviderChanged(ProviderManager.ProviderType provider) {
        Log.d(TAG, "Provider changed to: " + provider);
        showProvider(provider);
    }

}
