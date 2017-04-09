package com.zfwl.data.api;

import com.zfwl.entity.CPDModel;
import com.zfwl.entity.User;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ZZB on 2016/12/7.
 */

public interface CPDApi {
    @FormUrlEncoded
    @POST("app/member/getAdressOften.do")
    Observable<List<CPDModel>> getList(@Field("memberId") String memberId);

    @FormUrlEncoded
    @POST("app/member/saveAdressOften.do")
    Observable<CPDModel> save(@Field("memberId") String memberId,
                             @Field("id") String id,
                              @Field("fromProvinceId") String fromProvinceId,
                         @Field("fromCityId") String fromCityId,
                         @Field("fromCountyId") String fromCountyId,
                         @Field("toProvinceId") String toProvinceId,
                         @Field("toCityId") String toCityId,
                         @Field("toCountyId") String toCountyId);


    @FormUrlEncoded
    @POST("app/member/saveAdressOften.do")
    Observable<CPDModel> add(@Field("memberId") String memberId,
                              @Field("fromProvinceId") String fromProvinceId,
                              @Field("fromCityId") String fromCityId,
                              @Field("fromCountyId") String fromCountyId,
                              @Field("toProvinceId") String toProvinceId,
                              @Field("toCityId") String toCityId,
                              @Field("toCountyId") String toCountyId);

    @FormUrlEncoded
    @POST("app/member/delAdressOften.do")
    Observable<User> del(@Field("memberId") String memberId,
                                     @Field("id") String id);
}
