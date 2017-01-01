package com.zfwl.mvp.logistics;

import com.zfwl.entity.MyPublishEmptyCarListModel;
import com.zfwl.entity.MyQuotedModel;
import com.zfwl.mvp.MvpView;

import java.util.List;

public interface MyQuotedMvpView extends MvpView {

    void onGetListSuccess(MyQuotedModel d);

    void onGetListFailed(String errorMsg);


}
