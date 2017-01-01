package com.zfwl.data.api.retrofit.interceptor;

import com.zfwl.data.UserInfoManager;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 第一个Get请求添加默认memberId这个参数
 * Created by ZZB on 2016/12/31.
 */
public class DefaultParametersInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();
        String method = originalRequest.method();

        Request newRequest;
        switch (method) {
            case "GET":
                newRequest = interceptGet(originalRequest);
                break;
            case "POST":
                newRequest = interceptPost(originalRequest);
                break;
            default:
                newRequest = chain.request();
                break;
        }
        return chain.proceed(newRequest);
    }

    private Request interceptGet(Request originalRequest) {
        HttpUrl originalHttpUrl = originalRequest.url();
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("memberId", getMemberIdStr())
                .build();
        Request.Builder requestBuilder = originalRequest.newBuilder()
                .url(url);
        return requestBuilder.build();
    }

    private Request interceptPost(Request originalRequest) {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        FormBody b = (FormBody) originalRequest.body();
        for (int i = 0; i < b.size(); i++) {
            bodyBuilder.addEncoded(b.name(i), b.value(i));
        }
        bodyBuilder.add("memberId", getMemberIdStr());
//        bodyBuilder.addEncoded("memberId", getMemberIdStr());
        return originalRequest.newBuilder().post(bodyBuilder.build()).build();
    }

    public String getMemberIdStr() {
        return String.valueOf(UserInfoManager.INSTANCE.getMemberId());
    }
}
