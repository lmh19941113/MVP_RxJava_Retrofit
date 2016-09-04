package com.example.mvpdemo.presenter.presenter;

import android.content.Context;
import android.util.Log;

import com.example.mvpdemo.Config;
import com.example.mvpdemo.model.CallBack;
import com.example.mvpdemo.model.Rx;
import com.example.mvpdemo.model.bean.PhoneBean;
import com.example.mvpdemo.presenter.contract.FindPhoneContract;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by admin on 2016/9/2.
 */
public class FindPhonePresenter implements FindPhoneContract.Presenter, CallBack {

    private Observable observer;
    private Subscriber subscriber;

    private FindPhoneContract.View view;
    private Context context;

    public FindPhonePresenter(Context context, FindPhoneContract.View view) {
        this.context = context;
        this.view = view;
        this.view.setPresenter(this);
    }


    @Override
    public void unSubscriber() {
        if (subscriber != null && !subscriber.isUnsubscribed()) {//
            subscriber.unsubscribe();
        }
    }

    //请求成功
    @Override
    public <T> void onSuccess(int what, T t) {
        if (t instanceof PhoneBean) {
            PhoneBean phoneBean = (PhoneBean) t;
//            if (phoneBean.error_code == 0) {
                view.requestSuccess(what, phoneBean.result);
//            } else {
//                view.requestError(what);
//            }
        }
    }

    //请求失败
    @Override
    public void onError(int what, Throwable e) {
        Log.i("FindPhonePresenter", e.getMessage());
        view.requestError(what);
        view.dissLoading();
    }

    //请求完成，与onError不能共存，当网络请求成功后调用此方法
    @Override
    public void onCompleted(int what) {
        view.dissLoading();
    }


    //显示网络请求时加载的loading
    @Override
    public void loading(int what) {
        view.loading(what);
    }

    //进行网络请求
    @Override
    public void getPhoneData(String phone, String key) {
        observer = Rx.create().findPhoneForGet(phone, key);
        subscriber = Rx.sendHttp(Config.FIND_PHONE, observer, this);
    }
}
