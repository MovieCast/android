package xyz.moviecast.base.providers;

/**
 * Created by Bou's Laptop on 30/04/2018.
 */

public class SettingsProvider {
    private String headtext;
    private Boolean header;
    private String subText;

    public SettingsProvider(String headtext, String subText, Boolean header){
        this.headtext = headtext;
        this.header = header;
        this.subText = subText;
    }


    public Boolean getHeader() {
        return header;
    }

    public String getHeadtext() {
        return headtext;
    }

    public String getSubText() {
        return subText;
    }
}

