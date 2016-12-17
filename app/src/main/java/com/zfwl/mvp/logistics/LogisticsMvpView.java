package com.zfwl.mvp.logistics;

import com.zfwl.entity.LogisticsInfo;
import com.zfwl.mvp.MvpView;

import java.util.List;

/**
 * Created by ZZB on 2016/12/15.
 */
public interface LogisticsMvpView extends MvpView {

    void onRefreshLogisticsListSuccess(List<LogisticsInfo> logistics);

    void onLoadMoreLogisticsListSuccess(List<LogisticsInfo> logistics);

    void onLoadLogisticsListFailed(String errorMsg);


}
