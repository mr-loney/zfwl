package com.zfwl.mvp.sigup;

import com.zfwl.data.UserInfoManager;
import com.zfwl.data.api.SignUpApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.User;
import com.zfwl.mvp.BasePresenter;

import java.util.Random;

import rx.android.schedulers.AndroidSchedulers;

public class SignUpPresenter extends BasePresenter<SignUpView> {
    private SignUpApi mApi;
    private int randVeriftCode = -1;

    public SignUpPresenter() {
        mApi = ApiModule.INSTANCE.provideSignUpApi();
    }
    public SignUpPresenter(SignUpApi _mApi) {
        this.mApi = _mApi;
    }

    /**
     * 获取验证码
     *@author ZZB
     *created at 2015/8/10 14:54
     */
    public void getVerifyCode(String phoneNo){
        randVeriftCode = new Random().nextInt(8999) + 1000;

        if (phoneNo.length()!=11) {
            getMvpView().onGetVerifyCodeFailed("手机号码错误");
            return;
        }
        mApi.veriftCode(phoneNo, randVeriftCode+"")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(str -> {
                    getMvpView().onGetVerifyCodeSuccess();
                }, throwable -> {
                    getMvpView().onGetVerifyCodeFailed(throwable.getMessage());
                });
    }

    public void Register(String phoneNo,String vCode,String pwd){

        if ((vCode+"").equals(randVeriftCode)) {
            getMvpView().onRegisterFailed("验证码错误");
            return;
        }
        if (phoneNo.length() != 11) {
            getMvpView().onRegisterFailed("手机号码错误");
            return;
        }
        mApi.register(phoneNo, pwd)
                .doOnNext(this::saveUserInfo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(User -> {
                    getMvpView().onRegisterSuccess(User);
                }, throwable -> {
                    getMvpView().onRegisterFailed(throwable.getMessage());
                });
    }

    private void saveUserInfo(User user) {
        UserInfoManager.INSTANCE.saveUserInfo(user);
    }

    public void RegisterAddInfo(String userid,String phoneNo,String realName,int memberType){

        mApi.registerAddInfo(userid, phoneNo, realName, memberType+"")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(User -> {
                    getMvpView().onRegisterAddInfoSuccess(User);
                }, throwable -> {
                    getMvpView().onGetVerifyCodeFailed(throwable.getMessage());
                });
    }

    public void wxRegister(String phoneNumber, String verifyCode, String psw, String openId) {
        if ((verifyCode+"").equals(randVeriftCode)) {
            getMvpView().onRegisterFailed("验证码错误");
            return;
        }
        if (phoneNumber.length() != 11) {
            getMvpView().onRegisterFailed("手机号码错误");
            return;
        }
        mApi.wxRegister(phoneNumber, psw, openId)
                .doOnNext(this::saveUserInfo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(User -> {
                    getMvpView().onRegisterSuccess(User);
                }, throwable -> {
                    getMvpView().onRegisterFailed(throwable.getMessage());
                });
    }
}
