package xyz.moviecast.base.helpers;

import android.annotation.SuppressLint;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import xyz.moviecast.base.models.Movie;
import xyz.moviecast.base.providers.MovieProvider;
import xyz.moviecast.base.providers.response.models.general.Page;

public class MovieHelper implements Callback{

    private static final String TAG = "MOVIE_HELPER";

    private static MovieHelper instance;
    private static int id = 0;
    private ObjectMapper mapper;
    private MovieProvider movieProvider;

    private Map<Call, HelperData> jsonCallToHelperDataMap;
    private Map<String, ArrayList<String>> moviesIdMap;
    private Map<String, Movie> moviesMap;

    public static MovieHelper getInstance(){
        if(instance == null)
            instance = new MovieHelper();
        return instance;
    }

    @SuppressLint("UseSparseArrays")
    private MovieHelper(){
        //movieProvider = new MovieProvider();
        mapper = new ObjectMapper();

        jsonCallToHelperDataMap = new HashMap<>();
        moviesIdMap = new HashMap<>();
        moviesMap = new HashMap<>();
    }

    public HelperResult getMovie(String sorting, int position, HelperCallback callback){
        if(!moviesIdMap.keySet().contains(sorting)) {
            moviesIdMap.put(sorting, new ArrayList<String>());
        }else{
            ArrayList<String> ids = moviesIdMap.get(sorting);
            if(ids.size() > position){
                String movieId = ids.get(position);
                Movie movie = moviesMap.get(movieId);
                if(movie != null) {
                    Log.d(TAG, "getMovie: Returning a already cached movie: " +
                            Arrays.toString(ids.toArray()));
                    return new HelperResult<>(movie);
                }
            }
        }

        HelperData data = new HelperData(++id, position, sorting, callback);
        //Call call = movieProvider.providePage((position / 50) + 1, sorting, this);
        //jsonCallToHelperDataMap.put(call, data);
        return new HelperResult<>(id);
    }

    public int getMovieListSize(HelperCallback callback){
        HelperData data = new HelperData(++id, -1, null, callback);
        //Call call = movieProvider.getTotalAmountOfMedia(this);
        //jsonCallToHelperDataMap.put(call, data);
        return id;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        HelperData data = jsonCallToHelperDataMap.get(call);
        Integer id = data.getId();
        HelperCallback callback = data.getCallback();
        callback.onFailure(id, e);
        jsonCallToHelperDataMap.remove(call);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

        HelperData data = jsonCallToHelperDataMap.get(call);
        Integer id = data.getId();
        HelperCallback callback = data.getCallback();
        String sorting = data.getSorting();
        int position = data.getPosition();

        String string = response.body().string();
        try {
            Page page = mapper.readValue(string, Page.class);

            if (position == -1) {
                HelperResult<Integer> result = new HelperResult<>(page.getTotalResults());
                callback.onResponse(id, result);
            }else if (position >= 0) {
                Movie movie = page.getMovies().get(position % 50).toApplicationMovie();

                ArrayList<String> movies = moviesIdMap.get(sorting);
                while(movies.size() < position + 1)
                    movies.add(null);
                HelperResult<Movie> movieResult = new HelperResult<>(movie);

                movies.add(position, movie.getId());
                moviesMap.put(movie.getId(), movie);

                callback.onResponse(id, movieResult);
            }

            jsonCallToHelperDataMap.remove(call);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private class HelperData{
        private int id;
        private int position;
        private String sorting;
        private HelperCallback callback;

        HelperData(int id, int position, String sorting, HelperCallback callback) {
            this.id = id;
            this.position = position;
            this.sorting = sorting;
            this.callback = callback;
        }

        int getId() {
            return id;
        }

        int getPosition() {
            return position;
        }

        String getSorting() {
            return sorting;
        }

        HelperCallback getCallback() {
            return callback;
        }
    }
}
