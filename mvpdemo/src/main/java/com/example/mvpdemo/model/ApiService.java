package com.example.mvpdemo.model;


import com.example.mvpdemo.model.bean.PhoneBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;

/**
 * 请求的路径
 * Created by Administrator on 2016/8/1.
 */
public interface ApiService {
    @GET("MobilePlace/LookUp")
    Observable<PhoneBean> findPhoneForGet(@Query("mobileNumber") String phone, @Query("key") String key);
}
