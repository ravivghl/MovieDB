package com.moviedbdemo.webservice;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.moviedbdemo.model.CommonResponse;
import com.moviedbdemo.model.Configuration;
import com.moviedbdemo.model.MovieModel;
import com.moviedbdemo.utils.Const;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;


/**
 * Created by Mihir on 03/09/18.
 */

public class WebService {

    private static WebService instance;
    private String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private RestService restService;
    private RestClient restClient;

    private WebService(Context mContext) {
        this.mContext = mContext;
        RestClient.getInstance().init(mContext);
        restClient = RestClient.getInstance();
        restService = restClient.getRestService();
    }


    public static void init(Context mContext) {
        if (instance == null) {
            instance = new WebService(mContext);
        }
    }

    public static boolean isInitialized() {
        return instance == null;
    }

    public static WebService getInstance() {
        if (instance == null) {
            throw new RuntimeException(
                    "You must provide a valid context when initializing SDK");
        }

        return instance;
    }

    public void getConfiguration(final RestCallBack<Configuration> callBack) {
        restService.getConfiguration(Const.API_KEY).enqueue(new RetroCallBack<>(callBack, mContext));
    }
    public void getListOfMovies(String sortBy,final RestCallBack<CommonResponse> callBack) {
        restService.getList(sortBy,Const.API_KEY).enqueue(new RetroCallBack<>(callBack, mContext));
    }
    public void getMovieDetails(int id,final RestCallBack<MovieModel> callBack) {
        restService.getMovie(id,Const.API_KEY).enqueue(new RetroCallBack<>(callBack, mContext));
    }


}
