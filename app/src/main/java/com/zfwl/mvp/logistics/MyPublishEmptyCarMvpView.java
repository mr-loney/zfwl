package com.zfwl.mvp.logistics;

import com.zfwl.entity.MyPublishEmptyCarListModel;
import com.zfwl.mvp.MvpView;

import java.util.List;

public interface MyPublishEmptyCarMvpView extends MvpView {

    void onGetListSuccess(List<MyPublishEmptyCarListModel> datas);

    void onGetListFailed(String errorMsg);


}
