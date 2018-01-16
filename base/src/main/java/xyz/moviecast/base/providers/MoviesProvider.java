package xyz.moviecast.base.providers;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.VideoView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;
import xyz.moviecast.base.entities.Movie;

public class MoviesProvider extends ContentProvider<Movie> {

    private static final String TAG = "MOVIES_PROVIDER";

    private static final String PAGE = "page";
    private static final String RESULTS = "results";
    private static final String ID = "_id";
    private static final String TITLE = "title";
    private static final String YEAR = "year";
    private static final String RELEASED = "released";
    private static final String RATING = "rating";
    private static final String VOTES = "votes";
    private static final String WATCHING = "watching";
    private static final String PERCENTAGE = "percentage";
    private static final String IMAGES = "images";
    private static final String BACKGROUND = "background";
    private static final String POSTER = "poster";

    private static final String URL = "https://content.moviecast.xyz/movies/";

    @Override
    public Movie[] loadPage(int page) throws IOException{
        Request request = new Request.Builder().url(URL + page).build();
        Response response = httpClient.newCall(request).execute();
        if(response.code() != 200){
            return null;
        }
        return convertJSONToMovies(response.body().string());
    }

    @Override
    public Movie loadContent(Movie content) throws IOException {
        return null;
    }

    //TODO: Maybe save to JSON file on the phone if the page is either 1 or 2. Might be better for caching
    private Movie[] convertJSONToMovies(String json){
        try {
            JSONObject mainObject = new JSONObject(json);
            Log.i(TAG, "convertJSONToMovies: Currently on page: " + mainObject.getInt(PAGE));
            JSONArray mainJSONArray = mainObject.getJSONArray(RESULTS);
            Movie[] movies = new Movie[mainJSONArray.length()];

            for(int i = 0; i < mainJSONArray.length(); i++){
                JSONObject object = mainJSONArray.getJSONObject(i);
                String id = object.getString(ID);
                String title = object.getString(TITLE);
                String year = object.getString(YEAR);
                int released = object.getInt(RELEASED);
                JSONObject ratingObject = object.getJSONObject(RATING);
                int votes = ratingObject.getInt(VOTES);
                int watching = ratingObject.getInt(WATCHING);
                int percentage = ratingObject.getInt(PERCENTAGE);
                JSONObject imagesObject = object.getJSONObject(IMAGES);
                String backgroundImage = imagesObject.getString(BACKGROUND);
                String posterImage = imagesObject.getString(POSTER);
                movies[i] = new Movie(id, title, year, released, votes, watching, percentage, backgroundImage, posterImage);
            }
            return movies;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }
}
