package com.zfwl.mvp.cpd;

import com.zfwl.entity.Area;
import com.zfwl.entity.CPDModel;
import com.zfwl.mvp.MvpView;

import java.util.List;

public interface CPDMvpView extends MvpView {

    void onListLoaded(List<CPDModel> datas);
    void onListLoadedFail(String msg);

    void onAdded(CPDModel data);
    void onAddedFail(String msg);

    void onDel();
    void onDelFail(String msg);
}
