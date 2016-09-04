package com.example.mvpdemo.model;




import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * 网络请求工具类
 * Created by Administrator on 2016/8/1.
 */
public class Rx {
    private Rx() {
        throw new AssertionError("无法实例化该类");
    }

    private static final String BaseUrl = "http://api.avatardata.cn/";

    /**
     * 创建一个Retrofit对象
     *
     * @return
     */
    public static ApiService create() {
        return new Retrofit.Builder().baseUrl(BaseUrl)
                //添加gson适配器
                .addConverterFactory(GsonConverterFactory.create())
                        //添加RxAndroid与Retrofit的适配器，将两者关联起来
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(ApiService.class);
    }

    public static <T> Subscriber<T> sendHttp(final int what, Observable<T> observable, final CallBack callBack) {
        Subscriber<T> subscriber = new Subscriber<T>() {
            @Override
            public void onCompleted() {
                callBack.onCompleted(what);
            }

            @Override
            public void onError(Throwable e) {
//                LogHelper.i("请求错误", e.getMessage());
                callBack.onError(what, e);
            }

            @Override
            public void onNext(T t) {
                callBack.onSuccess(what, t);
            }

        };

        observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //异步请求之前的提示信息
                        callBack.loading(what);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return subscriber;
    }
}
