package com.zfwl.data.api;

import com.zfwl.entity.Area;
import com.zfwl.entity.User;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface AreaApi {
    @FormUrlEncoded
    @POST("/logistics/app/findDatas.do")
    Observable<List<Area>> findDatas(@Field("parentId") String parentId);
}
