package com.moviedbdemo.webservice;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moviedbdemo.utils.Const;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    public static RestClient restClient;
    private String TAG = this.getClass().getSimpleName();
    private Retrofit retrofit;
    private RestService restService;
    private Context mContext;
    private OkHttpClient httpClient;
    private boolean dummyCall = false;


    private RestClient() {
        final Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();

        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        final OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

        builder.readTimeout(10, TimeUnit.MINUTES);
        builder.connectTimeout(10, TimeUnit.MINUTES);
        builder.writeTimeout(10, TimeUnit.MINUTES);
            builder.addInterceptor(logging);


        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {


                Request original = chain.request();
                Request request;

                    request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .addHeader("Content-Type", "application/json")
                            .method(original.method(), original.body())
                            .build();


                Response response = chain.proceed(request);

                if (!response.isSuccessful()) {
                    WSException throwable = new WSException();
                    try {
                        String errorResponse = response.body().string();
                        Log.i(TAG, "Error_code = " + response.code());
                        Log.i(TAG, "Error = " + errorResponse);
                        JSONObject jsonObject = new JSONObject(errorResponse);
                        String message = jsonObject.optString("message");
                        throwable.setCode(response.code());
                        throwable.setServerMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throwable.setCode(response.code());
                        throwable.setServerMessage(response.message());
                    }
                    throw throwable;
                } /**else {
                 if (!dummyCall && chain.request().url().toString().contains("v1/profile")) {
                 dummyCall = true;
                 WSException throwable = new WSException();
                 throwable.setCode(Const.HttpError.ERROR_401);
                 throwable.setServerMessage("");
                 throw throwable;
                 }
                 }**/

                return response;
            }
        });
        httpClient = builder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();


        restService = retrofit.create(RestService.class);
    }


    public static RestClient getInstance() {
        if (restClient == null) {
            restClient = new RestClient();
        }

        return restClient;
    }

    public void init(Context context) {
        if (restClient == null)
            restClient = new RestClient();
        this.mContext = context;
    }

    public RestService getRestService() {
        return restService;
    }

    public OkHttpClient getOkHttpClient() {
        return httpClient;
    }
}