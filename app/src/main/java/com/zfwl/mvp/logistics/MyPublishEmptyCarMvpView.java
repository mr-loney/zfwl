package com.zfwl.mvp.logistics;

import com.zfwl.entity.MyPublishEmptyCarListModel;
import com.zfwl.mvp.MvpView;

import java.util.List;

public interface MyPublishEmptyCarMvpView extends MvpView {

    void onGetListSuccess(MyPublishEmptyCarListModel d);

    void onGetListFailed(String errorMsg);

    void onDelSuccess(MyPublishEmptyCarListModel.ListBean d);

    void onDelFail(String errorMsg);

}
