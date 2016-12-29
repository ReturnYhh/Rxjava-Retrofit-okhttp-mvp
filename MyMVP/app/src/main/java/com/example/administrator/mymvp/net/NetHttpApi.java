package com.example.administrator.mymvp.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求Api
 */
public class NetHttpApi {
    private Retrofit mRetrofit;
    //请求超时时间
    private static final int REQUEST_TIME = 10;
    private static NetHttpApi instance;

    private NetHttpApi() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(REQUEST_TIME, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIME, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIME, TimeUnit.SECONDS);
        mRetrofit = new Retrofit.Builder()
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(NetUrl.BASE_CLIENT_RES_URL)
                .build();

    }

    public static NetHttpApi getInstance() {
        if (instance == null) {
            synchronized (NetHttpApi.class) {
                if (instance == null) {
                    instance = new NetHttpApi();
                }
            }
        }
        return instance;
    }

    public <T> T getService(Class<T> service) {
        return mRetrofit.create(service);
    }
}
