package xyz.moviecast.base.providers;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.ArrayList;

import xyz.moviecast.base.R;

/**
 * Created by Bou's Laptop on 30/04/2018.
 */

public class SettingsProvider {
    private String headText;
    private Boolean header;
    private String subText;
    private ArrayList<Drawable> rescourses;
    private Drawable selected;

    public SettingsProvider(String headText, String subText, Boolean header, int resource){
        this.headText = headText;
        this.header = header;
        this.subText = subText;

        selected = rescourses.get(resource);
    }

    public SettingsProvider(String headText, Boolean header){
        this.headText = headText ;
        this.header = header;
    }


    public Boolean getHeader() {
        return header;
    }

    public String getHeadtext() {
        return headText;
    }

    public String getSubText() {
        return subText;
    }

    public Drawable getRescourse() {
        return selected;
    }
}

