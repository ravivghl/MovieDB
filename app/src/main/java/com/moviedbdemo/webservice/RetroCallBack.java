package com.moviedbdemo.webservice;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroCallBack<T> implements Callback<T> {

    private final RestCallBack<T> mCallback;

    private Context mContext;

    RetroCallBack(RestCallBack<T> callback, Context context) {
        this.mCallback = callback;
        this.mContext=context;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        this.mCallback.onResponse(response.body());

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        setFailure(this.mCallback, t);
    }

    private void setFailure(RestCallBack callBack, Throwable t) {
        if (callBack == null)
            return;

        if (t instanceof WSException) {
            callBack.onFailure((WSException) t);

        } else if (t instanceof UnknownHostException || t instanceof SocketTimeoutException) {
            WSException throwable = new WSException();
            throwable.setCode(-2);
            throwable.setServerMessage("Server is down");
            callBack.onFailure(throwable);

        }  else {
            t.printStackTrace();
            WSException throwable = new WSException();
            throwable.setCode(-1);
            throwable.setServerMessage(t.getMessage());
            callBack.onFailure(throwable);
        }
    }


}
