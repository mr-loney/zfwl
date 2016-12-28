package com.zfwl.data.api;

import com.zfwl.entity.AllzfwlModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface AddLogisticsApi {

    @GET("/app/empty/saveEmptyCar.do")
    Observable<AllzfwlModel> add(@Field("memberId") String memberId,
                                 @Field("fromProvinceId") String fromProvinceId,
                                 @Field("fromCityId") String fromCityId,
                                 @Field("fromCountyId") String fromCountyId,
                                 @Field("fromAddressName") String fromAddressName,
                                 @Field("toAddressJsonData") String toAddressJsonData,
                                 @Field("carNumber") int carNumber,
                                 @Field("loadNumber") double loadNumber,
                                 @Field("carLength") double carLength,
                                 @Field("goDate") String goDate);
}
