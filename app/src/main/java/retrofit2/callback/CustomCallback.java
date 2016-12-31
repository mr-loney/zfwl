package retrofit2.callback;

import com.zfwl.Exception.ResponseException;

import java.io.IOException;

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
                String msg = response.errorBody().string();

                onFailure(new ResponseException(code, msg));
            } catch (IOException e) {
                e.printStackTrace();
                onFailure(new ResponseException(e));
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (call.isCanceled()) {
            return;
        }
        onFailure(new ResponseException(t));
    }

    public abstract void onSuccess(T t);

    public abstract void onFailure(ResponseException exception);
}
