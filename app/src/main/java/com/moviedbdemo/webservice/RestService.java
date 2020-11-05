package com.moviedbdemo.webservice;


import com.moviedbdemo.model.CommonResponse;
import com.moviedbdemo.model.Configuration;
import com.moviedbdemo.model.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestService {
    @Headers("Cache-Control: public, max-stale=2419200")
    @GET("configuration")
    Call<Configuration> getConfiguration(@Query("api_key") String api_key);

    @GET("discover/movie")
    Call<CommonResponse> getList(@Query("sort_by") String sortBy,@Query("api_key") String api_key);

    @GET("movie/{id}")
    Call<MovieModel> getMovie(@Path("id") int id,@Query("api_key") String api_key);
}
