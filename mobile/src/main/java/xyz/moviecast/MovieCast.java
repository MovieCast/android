package xyz.moviecast;

import android.app.Application;
import android.content.Context;

public class MovieCast extends Application {

    private static MovieCast instance;

    public static MovieCast getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

}
