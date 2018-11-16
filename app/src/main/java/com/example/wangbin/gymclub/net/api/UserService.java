package com.example.wangbin.gymclub.net.api;

import com.example.wangbin.gymclub.model.User;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {
    @FormUrlEncoded
    @POST("/login")
    Observable<ResponseBody> login(@Field("phonenum") String phoneNum, @Field("password") String password);
//    @FormUrlEncoded
//    @POST("/register")
//    Observable<String> register(@Field("phonenum") String phoneNum,@Field("password") String password);
}
