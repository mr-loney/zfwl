package com.zfwl.data.api.retrofit.interceptor;

import com.zfwl.data.UserInfoManager;
import com.zfwl.util.FP;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 第一个Get请求添加默认memberId这个参数
 * Created by ZZB on 2016/12/31.
 */
public class DefaultParamtersInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest = chain.request();
        Request originalRequest = chain.request();
        String method = originalRequest.method();
        if(FP.eq("GET", method)){
            newRequest = interceptGet(originalRequest);
        }
        return chain.proceed(newRequest);
    }
    private Request interceptGet(Request originalRequest){
        HttpUrl originalHttpUrl = originalRequest.url();
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("memberId", UserInfoManager.INSTANCE.getMemberId() + "")
                .build();
        Request.Builder requestBuilder = originalRequest.newBuilder()
                .url(url);
        Request request = requestBuilder.build();
        return request;
    }
    private void interceptPost(){

    }
}
