package com.zfwl.mvp.sigup;

import com.zfwl.data.api.SignUpApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.User;
import com.zfwl.mvp.BasePresenter;

import rx.functions.Action1;

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

        if (phoneNo.length()!=11) {
            getMvpView().onGetVerifyCodeFailed("手机号码错误");
            return;
        }
<<<<<<< HEAD
//        mApi.veriftCode(phoneNo, randVeriftCode+"").subscribe(new Action1() {
//            @Override
//            public void call() {
//                getMvpView().onGetVerifyCodeSuccess();
//            }
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//                getMvpView().onGetVerifyCodeFailed(throwable.toString());
//            }
//        });
=======
        mApi.veriftCode(phoneNo, randVeriftCode+"").subscribe(new Action1() {
            @Override
            public void call(Object o) {
                getMvpView().onGetVerifyCodeSuccess();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                getMvpView().onGetVerifyCodeFailed(throwable.toString());
            }
        });
>>>>>>> origin/master
    }

    public void Register(String phoneNo,String vCode,String pwd){

        if (vCode != (randVeriftCode+"")) {
            getMvpView().onRegisterFailed("验证码错误");
            return;
        }
        if (phoneNo.length() != 11) {
            getMvpView().onRegisterFailed("手机号码错误");
            return;
        }
        mApi.register(phoneNo, pwd).subscribe(new Action1<User>() {
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

        mApi.registerAddInfo(userid, phoneNo, realName, memberType+"").subscribe(new Action1<User>() {
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
