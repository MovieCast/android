package xyz.moviecast.base.helpers;

import java.io.IOException;

public interface HelperCallback {
    void onFailure(int id, IOException e);
    void onResponse(int id, HelperResult result);
}
