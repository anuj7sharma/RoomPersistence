package com.room.api;

import com.room.utility.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author by Anuj Sharma on 12/4/2017.
 */

public class APIHandler {
    private static APIHandler instance;

    public static synchronized APIHandler getInstance() {
        if (instance == null) {
            instance = new APIHandler();
            instance.initialize();
        }
        return instance;
    }

    private APICallMethods callMethods;
    private OkHttpClient okHttpClient;
    private HttpLoggingInterceptor interceptor;
    private Retrofit retrofit;

    private void initialize() {
        try {
            interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .connectTimeout(25, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BaseURL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            callMethods = retrofit.create(APICallMethods.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public APICallMethods getAPIMethods() {
        return callMethods;
    }
}
