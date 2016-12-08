package com.zfwl.mvp.signup;

import android.content.Context;

import com.zfwl.common.SpManager;
import com.zfwl.model.Params;
import com.zfwl.model.PublicData;
import com.zfwl.mvp.MvpBasePresenter;
import com.zfwl.mvp.RequestData;
import com.zfwl.mvp.RequestError;
import com.zfwl.mvp.view.SignUpView;

import org.json.JSONObject;

public class SignUpPresenter extends BasePresenter<SignUpView> {
    private SignUpApi mApi;
    private int randVeriftCode = 1234;

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

        if (phoneNo.count!=11) {
            getMvpView().onLoginFailed("手机号码错误");
            return;
        }
        mApi.veriftCode(phone, randVeriftCode).subscribe(new Action1() {
            @Override
            public void call(User user) {
                getMvpView().onGetVerifyCodeSuccess();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                getMvpView().onGetVerifyCodeFailed(throwable.toString());
            }
        });
    }

    public void Register(String phoneNo,String vCode,String pwd){

        if (vCode != randVeriftCode) {
            getMvpView().onLoginFailed("验证码错误");
            return;
        }
        if (phoneNo.count != 11) {
            getMvpView().onLoginFailed("手机号码错误");
            return;
        }
        mApi.register(phone, pwd).subscribe(new Action1<>() {
            @Override
            public void call(User user) {
                getMvpView().onRegisterSuccess(user);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                getMvpView().onRegisterFailed(throwable.toString());
            }
        });
    }
    public void RegisterAddInfo(String userid,String phoneNo,String realName,int memberType){

        mApi.register(userid, phoneNo, realName, memberType).subscribe(new Action1<>() {
            @Override
            public void call(User user) {
                getMvpView().onRegisterAddInfoSuccess(user);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                getMvpView().onRegisterAddInfoFailed(throwable.toString());
            }
        });
    }

}
