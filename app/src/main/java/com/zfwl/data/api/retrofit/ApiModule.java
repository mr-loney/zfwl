package com.zfwl.data.api.retrofit;

import com.github.simonpercic.oklog3.OkLogInterceptor;
import com.google.gson.Gson;
import com.zfwl.common.MyLog;
import com.zfwl.data.api.AddLogisticsApi;
import com.zfwl.data.api.AreaApi;
import com.zfwl.data.api.CPDApi;
import com.zfwl.data.api.LoginApi;
import com.zfwl.data.api.LogisticsApi;
import com.zfwl.data.api.MyPublishEmptyCarApi;
import com.zfwl.data.api.MyQuotedApi;
import com.zfwl.data.api.OrderApi;
import com.zfwl.data.api.SettingsApi;
import com.zfwl.data.api.SignUpApi;
import com.zfwl.data.api.WJApi;
import com.zfwl.data.api.pay.WxPayApi;
import com.zfwl.data.api.retrofit.interceptor.DefaultParametersInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.zfwl.ZfwlConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by ZZB on 2016/12/7.
 */

public class ApiModule {

    public static ApiModule INSTANCE = new ApiModule();
    public static final String BASE_URL = "https://www.huohuitong.com/";
    private static final int CONNECT_TIMEOUT = 15;
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;

    private ApiModule() {
    }

    public Retrofit provideRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(ZfwlConverterFactory.create(new Gson()))//json converter always say yes, put it to the last
//                    .addConverterFactory(GsonConverterFactory.create())
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

    public WxPayApi provideWxPayApi() {
        return ApiModule.INSTANCE.provideRetrofit().create(WxPayApi.class);
    }

    public AreaApi provideAreaApi() {
        return ApiModule.INSTANCE.provideRetrofit().create(AreaApi.class);
    }

    public CPDApi cpdApi() {
        return ApiModule.INSTANCE.provideRetrofit().create(CPDApi.class);
    }

    public MyPublishEmptyCarApi publishEmptyCarApi() {
        return ApiModule.INSTANCE.provideRetrofit().create(MyPublishEmptyCarApi.class);
    }

    public WJApi wjApi() {
        return ApiModule.INSTANCE.provideRetrofit().create(WJApi.class);
    }

    public AddLogisticsApi addLogisticsApi() {
        return ApiModule.INSTANCE.provideRetrofit().create(AddLogisticsApi.class);
    }

    public OrderApi provideOrderApi() {
        return ApiModule.INSTANCE.provideRetrofit().create(OrderApi.class);
    }

    public MyQuotedApi quotedApi() {
        return ApiModule.INSTANCE.provideRetrofit().create(MyQuotedApi.class);
    }

    public LogisticsApi provideLogisticsApi() {
        return ApiModule.INSTANCE.provideRetrofit().create(LogisticsApi.class);
    }
    public SettingsApi provideSettingsApi(){
        return ApiModule.INSTANCE.provideRetrofit().create(SettingsApi.class);
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
                .addInterceptor(new DefaultParametersInterceptor())
                .addInterceptor(provideLoggingInterceptor())
                .addInterceptor(provideOkLogInterceptor())
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

    private Interceptor provideOkLogInterceptor() {
        OkLogInterceptor okLogInterceptor = OkLogInterceptor.builder().build();
        return okLogInterceptor;
    }


}
