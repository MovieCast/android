package xyz.moviecast.base.providers.models.movies;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class Torrents {

    private Map<String, Quality> qualities = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Quality> getQualities(){
        return qualities;
    }

    @JsonAnySetter
    public void setQualities(String name, Quality quality){
        qualities.put(name, quality);
    }


}
