package com.zfwl.mvp.logistics;

import com.zfwl.data.api.LogisticsApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.request.LogisticsRequest;
import com.zfwl.mvp.BasePresenter;
import com.zfwl.util.FP;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZZB on 2016/12/15.
 */
public class LogisticsPresenter extends BasePresenter<LogisticsMvpView> {

    private static final String TAG = "LogisticsPresenter";
    private static final int PAGE_SIZE = 10;
    private LogisticsApi mLogisticsApi;
    private int mPage;

    public LogisticsPresenter() {
        mLogisticsApi = ApiModule.INSTANCE.provideLogisticsApi();
    }

    public void refreshLogisticsList() {
        mPage = 0;
        getMvpView().onRefreshLogisticsListSuccess(null);
    }

    public void loadMoreLogisticsList() {
        mPage++;
    }

    private Map<String, String> getQueryMap(LogisticsRequest request) {
        Map<String, String> params = new HashMap<>();
        if(FP.notEmpty(request.getFromCity())){
            params.put("fromCity", request.getFromCity());
        }
//        if(FP.notEmpty(request.getFromCounty())){
//            params.put("fromCounty")
//        }
        return params;
    }
}
