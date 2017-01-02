package com.zfwl.mvp.logistics;

import com.zfwl.entity.DriverQuotedModel;
import com.zfwl.entity.LogisticsInfo;
import com.zfwl.mvp.MvpView;

import java.util.List;

public interface DriverQuotedMvpView extends MvpView {

    void onAddSuccess(DriverQuotedModel d);

    void onAddFailed(String errorMsg);


}
