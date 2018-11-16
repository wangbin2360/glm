package com.example.wangbin.gymclub.net;

import com.example.wangbin.gymclub.net.api.UserService;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static final String BASE_URL = "http://172.27.141.31:8110/";
    private static volatile Retrofit RETROFIT_INSTANCE = null;
    public static Retrofit getRetrofit(){
        if(RETROFIT_INSTANCE == null) {
            synchronized (RetrofitUtils.class) {
                if(RETROFIT_INSTANCE == null) {
                    RETROFIT_INSTANCE =new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return RETROFIT_INSTANCE;
    }

    public static Observable<ResponseBody> login(String phoneNum, String password){
        UserService userService = getRetrofit().create(UserService.class);
        return userService.login(phoneNum,password);
    }

    public static Observable<ResponseBody> register(String phoneNum,String password){
        UserService userService = getRetrofit().create(UserService.class);
        return userService.register(phoneNum,password);
    }
}
