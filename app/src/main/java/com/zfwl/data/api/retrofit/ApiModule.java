package com.zfwl.data.api.retrofit;

import com.google.gson.Gson;
import com.zfwl.data.api.LoginApi;
import com.zfwl.data.api.SignUpApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.zfwl.ZfwlConverterFactory;
import rx.schedulers.Schedulers;

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
                    .addConverterFactory(ZfwlConverterFactory.create(new Gson()))
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .client(provideOkHttpClient())
                    .build();
        }
        return mRetrofit;
    }

    public LoginApi provideLoginApi() {
        return ApiModule.INSTANCE.provideRetrofit().create(LoginApi.class);
    }
    public SignUpApi provideSignUpApi() {
        return ApiModule.INSTANCE.provideRetrofit().create(SignUpApi.class);
    }


    private OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }
}
