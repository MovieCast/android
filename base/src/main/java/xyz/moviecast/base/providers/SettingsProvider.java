package xyz.moviecast.base.providers;

/**
 * Created by Bou's Laptop on 30/04/2018.
 */

public class SettingsProvider {
    private String headText;
    private Boolean header;
    private String subText;
    private int resource;

    public SettingsProvider(String headText, String subText, Boolean header, int resource){
        this.headText = headText;
        this.header = header;
        this.subText = subText;
        this.resource = resource;
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

    public Integer getRescourse() {
        return resource;
    }
}

