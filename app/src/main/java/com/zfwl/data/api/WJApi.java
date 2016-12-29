package com.zfwl.data.api;

import com.zfwl.entity.CPDModel;
import com.zfwl.entity.User;
import com.zfwl.entity.WJModel;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ZZB on 2016/12/7.
 */

public interface WJApi {
    @FormUrlEncoded
    @POST("app/member/getAdressOften.do")
    Observable<List<WJModel>> getList(@Field("memberId") String memberId);

}
