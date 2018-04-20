package xyz.moviecast.base.helpers;

public class HelperResult<T> {

    private T data;

    HelperResult(T data){
        this.data = data;
    }

    public T getData(){
        return data;
    }

}
