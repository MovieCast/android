package xyz.moviecast.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.moviecast.R;

public class MediaCatalogFragment extends Fragment{

    private static final String TAG = "MEDIA_CATALOG";
    public static final String KEY_TYPE = "TYPE";
    public static final String KEY_SORTING = "SORTING";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_media_catalog, container, false);
    }
}
