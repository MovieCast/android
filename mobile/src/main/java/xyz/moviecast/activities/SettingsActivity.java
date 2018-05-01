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
import xyz.moviecast.adapters.SettingAdapter;
import xyz.moviecast.base.providers.SettingsProvider;

public class SettingsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView settingsList;

    public static Intent getIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        settingsList  = (ListView) findViewById(R.id.settings);

        ArrayList<SettingsProvider> settings = new ArrayList<>();
        settings.add(new SettingsProvider("User Interface", "", true));
        settings.add(new SettingsProvider("Default Language", "English", false));
        settings.add(new SettingsProvider("Start Screen", "movies", false));
        settings.add(new SettingsProvider("subtitles", "", true));
        settings.add(new SettingsProvider("Default Language", "English", false));
        settings.add(new SettingsProvider("Size", "24px", false));


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final SettingAdapter adapter = new SettingAdapter(this, settings);
        settingsList.setAdapter(adapter);
    }
}


