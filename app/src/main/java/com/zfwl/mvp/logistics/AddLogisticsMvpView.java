package com.zfwl.mvp.logistics;

import com.zfwl.entity.LogisticsInfo;
import com.zfwl.mvp.MvpView;

import java.util.List;

public interface AddLogisticsMvpView extends MvpView {

    void onAddLogisticsSuccess();

    void onAddLogisticsFailed(String errorMsg);


}
