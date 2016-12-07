package com.zfwl.presenter.login;

import com.zfwl.data.api.LoginApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.mvp.login.LoginMvpView;
import com.zfwl.mvp.login.LoginPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

/**
 * Created by ZZB on 2016/12/7.
 */
public class LoginPresenterTest {
    private final NetworkBehavior mNetworkBehavior = NetworkBehavior.create();
    @Mock
    private LoginMvpView mockView;
    @Mock
    private ApiModule mockModule;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockRetrofit mockRetrofit = new MockRetrofit.Builder(ApiModule.INSTANCE.provideRetrofit())
                .networkBehavior(mNetworkBehavior).build();
        BehaviorDelegate<LoginApi> delegate = mockRetrofit.create(LoginApi.class);
        MockLoginApi mockApi = new MockLoginApi(delegate);
        Mockito.when(mockModule.provideLoginApi()).thenReturn(mockApi);
    }

    @Test
    public void test() {
        LoginPresenter loginPresenter = new LoginPresenter();
        loginPresenter.attachView(mockView);
        loginPresenter.login("a", "b");
        Mockito.verify(mockView).onLoginFailed(Mockito.anyString());

    }
}
