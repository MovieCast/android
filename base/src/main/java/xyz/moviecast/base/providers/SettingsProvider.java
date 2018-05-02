package xyz.moviecast.base.providers;

import java.util.ArrayList;

/**
 * Created by Bou's Laptop on 30/04/2018.
 */

public class SettingsProvider {
    private String headText;
    private boolean header;
    private String subText;
    private int resource;
    private boolean slider;
    private ArrayList<String> modes;

    //normal setting constructor
    public SettingsProvider(String headText, String subText, boolean header, int resource){
        this.headText = headText;
        this.header = header;
        this.subText = subText;
        this.resource = resource;
    }

    public SettingsProvider(String headText, boolean slider, int resource, ArrayList modes){
        this.slider = slider;
        this.headText = headText;
        this.resource = resource;
        this.header = false;
        this.modes = modes;
    }

    //header constructor
    public SettingsProvider(String headText, boolean header){
        this.headText = headText ;
        this.header = header;
    }

    public String getMode(int i) {
        return modes.get(i);
    }

    public boolean isSlider() {
        return slider;
    }

    public boolean getHeader() {
        return header;
    }

    public String getHeadtext() {
        return headText;
    }

    public String getSubText() {
        return subText;
    }

    public Integer getRescourse() {
        return resource;
    }
}

