package retrofit2.converter.zfwl;

import com.google.gson.TypeAdapter;
import java.io.IOException;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Converter;

/**
 * {
 * "status": 200,
 * "message": "msg",
 * "result": {json}
 * }
 * Created by ZZB on 2016/12/8.
 */

public class ZfwlResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;

    public ZfwlResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String body = value.string();
            JSONObject json = new JSONObject(body);
            int status = json.optInt("status");
            String msg = json.optString("msg", "");
            if (status == 200) {
                if (json.has("result")) {
                    Object data = json.get("result");
                    body = data.toString();
                    return adapter.fromJson(body);
                } else {
                    return (T) msg;
                }
            } else {
                throw new RuntimeException(msg);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            value.close();
        }
    }
}
