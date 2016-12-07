package com.zfwl.data.api.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ZZB on 2016/12/7.
 */

public class ApiModule {

    public static ApiModule INSTANCE = new ApiModule();
    private static final String BASE_URL = "http://api.zflogistics.com/";
    private Retrofit mRetrofit;

    private ApiModule() {
    }

    public Retrofit provideRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(provideOkHttpClient())
                    .build();
        }
        return mRetrofit;
    }

    private OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }
}
