package com.example.mvpdemo.model;

/**
 * Created by Administrator on 2016/8/2.
 */
public interface CallBack {
    //请求成功
    <T> void onSuccess(int what, T t);

    //请求失败
    void onError(int what, Throwable e);

    //请求取消，与onError只能二存一
    void onCompleted(int what);

    void loading(int what);
}
