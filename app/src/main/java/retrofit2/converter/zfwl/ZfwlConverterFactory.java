package retrofit2.converter.zfwl;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by ZZB on 2016/12/8.
 */

public class ZfwlConverterFactory extends Converter.Factory {
    private final Gson gson;

    public static ZfwlConverterFactory create(Gson gson) {
        return new ZfwlConverterFactory(gson);
    }

    private ZfwlConverterFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (String.class.equals(type)) {
            return ResponseBody::string;
        }else{
            TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
            return new ZfwlResponseBodyConverter<>(adapter);
        }
    }
}
