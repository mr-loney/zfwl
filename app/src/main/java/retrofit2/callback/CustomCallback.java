package retrofit2.callback;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ZZB on 2016/12/27.
 */
public abstract class CustomCallback<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (call.isCanceled()) {
            return;
        }
        int code = response.code();
        if (response.isSuccessful() && code == 200) {
            onSuccess(response.body());
        } else {
            try {
                String msg;
                switch (code) {
                    case 404:
                    case 500:
                        msg = String.valueOf(code);
                        break;
                    default:
                        msg = response.errorBody().string();
                        break;
                }
                onFailure(code, msg);
            } catch (IOException e) {
                e.printStackTrace();
                onFailure(0, e.getMessage());
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (call.isCanceled()) {
            return;
        }
        String msg;
        if(t instanceof SocketTimeoutException){
            msg = "请求超时";
        }else{
            msg = t.getMessage();
        }
        onFailure(0, msg);
    }

    public abstract void onSuccess(T t);

    public abstract void onFailure(int code, String msg);
}
