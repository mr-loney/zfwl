package com.zfwl.data;

import android.content.Context;

import com.zfwl.ZfwlApplication;
import com.zfwl.data.sp.GlobalPref;
import com.zfwl.data.sp.UserPref;
import com.zfwl.entity.User;
import com.zfwl.push.PushConfig;
import com.zfwl.util.GsonUtils;
import com.zzb.easysp.generated.EasySPUserPref;

/**
 * Created by ZZB on 2016/12/25.
 */
public class UserInfoManager {
    public static UserInfoManager INSTANCE = new UserInfoManager();
    private Context mContext = ZfwlApplication.APP_CONTEXT;
    private UserPref mUserPref;
    private User mUser;

    private UserInfoManager() {
        mUserPref = EasySPUserPref.create(mContext);
    }

    public void init(long memberId) {
        mUserPref = EasySPUserPref.create(mContext, memberId + "");
        mUser = GsonUtils.jsonToObject(mUserPref.getUserJson(), User.class);
    }

    public void saveUserInfo(User user) {
        mUser = user;
        //每个用户存一份sp
        mUserPref = EasySPUserPref.create(mContext, user.getId() + "");
        mUserPref.setUserJson(GsonUtils.objectToJson(user));
        GlobalPref.get(mContext).setLastLoginMemberId(user.getId());
    }

    public boolean hasLogin() {
        return getMemberId() > 0;
    }

    public User getUserInfo() {
        if (mUser == null) {
            User cachedUser = GsonUtils.jsonToObject(mUserPref.getUserJson(), User.class);
            mUser = cachedUser;
        }
        return mUser;
    }

    public long getMemberId() {
        return getUserInfo().getId();
    }

    public void clearOnLogout() {
        INSTANCE = new UserInfoManager();
        mUserPref = null;
        GlobalPref.get(mContext).setLastLoginMemberId(0);
        PushConfig.clearTag(mContext);
    }
}
