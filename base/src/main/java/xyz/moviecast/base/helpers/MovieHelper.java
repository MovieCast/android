package xyz.moviecast.base.helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import xyz.moviecast.base.models.Movie;
import xyz.moviecast.base.providers.MovieProvider;
import xyz.moviecast.base.providers.models.movies.Page;

public class MovieHelper implements Callback {

    private static final String TAG = "MOVIE_HELPER";

    private static MovieHelper instance;
    private static int id = 0;
    private ObjectMapper mapper;
    private MovieProvider provider;
    private Map<Call, HelperData> callToHelperDataMap;

    private Map<String, ArrayList<String>> moviesIdMap;
    private Map<String, Movie> moviesMap;

    public static MovieHelper getInstance(Context context){
        if(instance == null)
            instance = new MovieHelper(context);
        return instance;
    }

    @SuppressLint("UseSparseArrays")
    private MovieHelper(Context context){
        provider = new MovieProvider(context);
        mapper = new ObjectMapper();

        callToHelperDataMap = new HashMap<>();
        moviesIdMap = new HashMap<>();
        moviesMap = new HashMap<>();
    }

    public int getMovie(String sorting, int position, MovieHelperCallback callback){
        if(!moviesIdMap.keySet().contains(sorting)) {
            moviesIdMap.put(sorting, new ArrayList<String>());
            Log.d(TAG, "getMovie: Added ArrayList to the map. Size of map is now: " + moviesIdMap.keySet().size());
        }else{
            ArrayList<String> ids = moviesIdMap.get(sorting);
            if(ids.size() >= position){
                String movieId = ids.get(position);
                Movie movie = moviesMap.get(movieId);
                if(movie != null) {
                    MovieHelperResult<Movie> result = new MovieHelperResult<>(movie);
                    callback.onResponse(++id, result);
                    return id;
                }
            }
        }

        try {
            HelperData data = new HelperData(++id, position, sorting, callback);
            Call call = provider.providePage((position / 50) + 1, sorting, this);
            callToHelperDataMap.put(call, data);
        } catch (IOException e) {
            e.printStackTrace();
            callback.onFailure(id, e);
        }
        return id;
    }

    public int getMovieListSize(MovieHelperCallback callback){
        try{
            HelperData data = new HelperData(++id, -1, null, callback);
            Call call = provider.getTotalAmountOfMedia(this);
            callToHelperDataMap.put(call, data);
        } catch (IOException e){
            e.printStackTrace();
            callback.onFailure(id, e);
        }
        return id;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        HelperData data = callToHelperDataMap.get(call);
        Integer id = data.getId();
        MovieHelperCallback callback = data.getCallback();
        callback.onFailure(id, e);
        callToHelperDataMap.remove(call);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        HelperData data = callToHelperDataMap.get(call);
        Integer id = data.getId();
        MovieHelperCallback callback = data.getCallback();
        String sorting = data.getSorting();
        int position = data.getPosition();

        String string = response.body().string();
        Page page = mapper.readValue(string, Page.class);

        if (position == -1) {
            MovieHelperResult<Integer> result = new MovieHelperResult<>(page.getTotalResults());
            callback.onResponse(id, result);
        }else if (position >= 0) {
            Movie movie = page.getMovies().get(position % 50).toApplicationMovie();

            ArrayList<String> movies = moviesIdMap.get(sorting);
            while(movies.size() < position + 1)
                movies.add(null);
            movies.add(position, movie.getId());
            moviesMap.put(movie.getId(), movie);

            MovieHelperResult<Movie> result = new MovieHelperResult<>(movie);
            //TODO: if the movie has no image add the images using the ImageProvider
            callback.onResponse(id, result);
        }

        callToHelperDataMap.remove(call);
    }

    private class HelperData{
        private int id;
        private int position;
        private String sorting;
        private MovieHelperCallback callback;

        HelperData(int id, int position, String sorting, MovieHelperCallback callback) {
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

        MovieHelperCallback getCallback() {
            return callback;
        }
    }

    public class MovieHelperResult<T>{
        private T data;

        MovieHelperResult(T data){
            this.data = data;
        }

        public T getData(){
            return data;
        }
    }

    public interface MovieHelperCallback {
        void onFailure(int id, IOException e);
        void onResponse(int id, MovieHelperResult result);
    }
}
