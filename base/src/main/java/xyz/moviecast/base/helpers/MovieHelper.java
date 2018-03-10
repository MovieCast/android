package xyz.moviecast.base.helpers;

import android.annotation.SuppressLint;
import android.content.Context;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import xyz.moviecast.base.models.Movie;
import xyz.moviecast.base.providers.MovieProvider;

public class MovieHelper implements Callback {

    private static MovieHelper instance;
    private static int id = 0;
    private ObjectMapper mapper;
    private MovieProvider provider;
    private Map<Call, Integer> callToIdMap;
    private Map<Integer, MovieHelperCallback> idToCallbackMap;

    public static MovieHelper getInstance(Context context){
        if(instance == null)
            instance = new MovieHelper(context);
        return instance;
    }

    @SuppressLint("UseSparseArrays")
    private MovieHelper(Context context){
        provider = new MovieProvider(context);
        callToIdMap = new HashMap<>();
        idToCallbackMap = new HashMap<>();
        mapper = new ObjectMapper();
    }

    public int getMovie(String sorting, int position, MovieHelperCallback callback){

        try {
            Call call = provider.providePage((position / 50) + 1, sorting, this);
            callToIdMap.put(call, ++id);
            idToCallbackMap.put(id, callback);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    public int getMovieListSize(){
        return 100;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        Integer integer = callToIdMap.get(call);
        MovieHelperCallback callback = idToCallbackMap.get(integer);
        callback.onFailure(integer, e);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        Integer integer = callToIdMap.get(call);
        MovieHelperCallback callback = idToCallbackMap.get(integer);
        xyz.moviecast.base.providers.models.movies.Movie movie = mapper.readValue(
                response.body().string(), xyz.moviecast.base.providers.models.movies.Movie.class);
        MovieHelperResult<Movie> result = new MovieHelperResult<>(movie.toApplicationMovie());
        callback.onResponse(integer, result);
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
