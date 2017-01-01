package com.zfwl.mvp.wj;

import com.zfwl.entity.WJModel;
import com.zfwl.mvp.MvpView;

import java.util.List;

public interface WJMvpView extends MvpView {

    void onListLoaded(WJModel d);
    void onListLoadedFail(String msg);
}
