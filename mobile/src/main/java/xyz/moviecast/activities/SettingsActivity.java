package xyz.moviecast.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;

import xyz.moviecast.R;
import xyz.moviecast.base.providers.SettingsProvider;

public class SettingsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView settings;

    public static Intent getIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        settings = (ListView) findViewById(R.id.settings_bar);

        ArrayList<SettingsProvider> settings = new ArrayList<>();
        settings.add(new SettingsProvider("User interface", "", true));
        settings.add(new SettingsProvider("Default Language", "English", false));

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_settings);
    }
}


