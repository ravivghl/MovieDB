package com.moviedbdemo.webservice;

public interface RestCallBack<T>  {

    void onResponse(T response);

    void onFailure(WSException wse);
}
