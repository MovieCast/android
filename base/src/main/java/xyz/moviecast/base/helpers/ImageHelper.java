package xyz.moviecast.base.helpers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ImageHelper implements Callback {

    private Map<Call, HelperData> callToHelperDataMap;
    private Map<String, byte[]> imageMap;
    //private ImageProvider imageProvider;

    private static ImageHelper instance;
    private static int id = 0;

    public static ImageHelper getInstance() {
        if(instance == null)
            instance = new ImageHelper();
        return instance;
    }

    private ImageHelper(){
        callToHelperDataMap = new HashMap<>();
        imageMap = new HashMap<>();
        //imageProvider = new ImageProvider();
    }

    public HelperResult getImage(String imageUrl, HelperCallback callback){
        if(imageMap.containsKey(imageUrl)){
            HelperResult<byte[]> result = new HelperResult<>(imageMap.get(imageUrl));
            return result;
        }

        HelperData data = new HelperData(++id, imageUrl, callback);
        //Call call = imageProvider.provideImage(imageUrl, this);
        //callToHelperDataMap.put(call, data);

        return new HelperResult<>(id);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        HelperData data = callToHelperDataMap.get(call);
        data.getCallback().onFailure(data.getId(), e);
        callToHelperDataMap.remove(data);
    }

    @Override
    public void onResponse(Call call, Response response) {
        HelperData data = callToHelperDataMap.get(call);
        int id = data.getId();
        HelperCallback callback = data.getCallback();
        String imageUrl = data.getImageUrl();

        try {
            byte[] bytes = response.body().bytes();
            imageMap.put(imageUrl, bytes);
            HelperResult<byte[]> result = new HelperResult<>(bytes);
            callback.onResponse(id, result);
        } catch (IOException e) {
            callback.onFailure(id, e);
            e.printStackTrace();
        }
    }

    private class HelperData{
        private int id;
        private String imageUrl;
        private HelperCallback callback;

        HelperData(int id, String imageUrl, HelperCallback callback) {
            this.id = id;
            this.imageUrl = imageUrl;
            this.callback = callback;
        }

        public int getId() {
            return id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public HelperCallback getCallback() {
            return callback;
        }
    }
}
