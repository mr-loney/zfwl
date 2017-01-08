package com.zfwl.mvp.logistics;

import com.zfwl.data.UserInfoManager;
import com.zfwl.entity.CPDModel;
import com.zfwl.entity.MyPublishEmptyCarListModel;
import com.zfwl.entity.MyQuotedModel;
import com.zfwl.mvp.MvpView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

public interface MyQuotedMvpView extends MvpView {

    void onGetListSuccess(MyQuotedModel d);

    void onGetListFailed(String errorMsg);

    void onDel();
    void onDelFail(String msg);
}
