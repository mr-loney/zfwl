package com.zfwl.util;

import com.zfwl.entity.LogisticsInfo.ListBean.AddressInfoListBean;

import java.util.List;

/**
 * Created by ZZB on 2017/1/1.
 */
public class AddressUtils {

    public static String getToAddressStr(List<AddressInfoListBean> address) {
        if (FP.empty(address)) {
            return "";
        } else {
            StringBuilder str = new StringBuilder();
            int size = FP.size(address);
            for (int i = 0; i < size; i++) {
                AddressInfoListBean info = address.get(i);
                str.append(info.getToProvinceName()).append(info.getToCityName()).append(info.getToCountyName()).append(info.getToDetail());
                if (i != size - 1) {
                    str.append("\n");
                }
            }
            return str.toString();
        }
    }

    public static String getFromAddressStr(List<AddressInfoListBean> address) {
        if (FP.empty(address)) {
            return "";
        } else {
            AddressInfoListBean info = address.get(0);
            return info.getFromProvinceName() + info.getFromCityName() + info.getFromCountyName() + info.getFromDetail();
        }
    }
}
