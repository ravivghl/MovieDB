package com.moviedbdemo;

import android.app.Application;

import com.moviedbdemo.webservice.WebService;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        WebService.init(this);
    }
}
