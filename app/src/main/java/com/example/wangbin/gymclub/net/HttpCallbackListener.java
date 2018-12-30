package com.example.wangbin.gymclub.net;

/**
 * Created by Administrator on 2017/5/5.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
