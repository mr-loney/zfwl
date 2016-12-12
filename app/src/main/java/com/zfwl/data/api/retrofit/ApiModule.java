package com.zfwl.data.api.retrofit;

import com.google.gson.Gson;
import com.zfwl.common.MyLog;
import com.zfwl.data.api.LoginApi;
import com.zfwl.data.api.SignUpApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
    private static final String BASE_URL = "http://139.129.218.83:90/logistics/";
    private static final int CONNECT_TIMEOUT = 15;
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;

    private ApiModule() {
    }

    public Retrofit provideRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(ZfwlConverterFactory.create(new Gson()))//json converter always say yes, put it to the last
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
        if (mOkHttpClient == null) {
            initOkHttpClient();
        }
        return mOkHttpClient;
    }

    private void initOkHttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.retryOnConnectionFailure(true)
                .addInterceptor(provideLoggingInterceptor())
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        mOkHttpClient = builder.build();
    }

    private HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                MyLog.i("OkHttp", message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

}
