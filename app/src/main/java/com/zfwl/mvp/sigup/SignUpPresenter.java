package com.zfwl.mvp.sigup;

import com.zfwl.data.api.SignUpApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.User;
import com.zfwl.mvp.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
        mApi.veriftCode(phoneNo, randVeriftCode+"").enqueue(new Callback() {
        @Override
        public void onResponse(Call call, Response response) {
            getMvpView().onGetVerifyCodeSuccess();
        }
        @Override
        public void onFailure(Call call, Throwable t) {
            getMvpView().onGetVerifyCodeFailed(t.toString());
        }
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
        mApi.register(phoneNo, pwd).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                getMvpView().onRegisterSuccess(response.body());
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                getMvpView().onRegisterFailed(t.getMessage());
            }
        });

    }
    public void RegisterAddInfo(String userid,String phoneNo,String realName,int memberType){

        mApi.registerAddInfo(userid, phoneNo, realName, memberType+"").enqueue(new Callback<User>() {
        @Override
        public void onResponse(Call<User> call, Response<User> response) {
            getMvpView().onRegisterAddInfoSuccess(response.body());
        }
        @Override
        public void onFailure(Call<User> call, Throwable t) {
            getMvpView().onRegisterAddInfoFailed(t.getMessage());
        }
    });
    }

}
