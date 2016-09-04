package com.example.mvpdemo.presenter.contract;

import com.example.mvpdemo.presenter.BasePresenter;
import com.example.mvpdemo.presenter.BaseView;

/**
 * Created by admin on 2016/9/2.
 */
public interface FindPhoneContract {
    interface View extends BaseView<Presenter> {
        void loading(int what);

        void dissLoading();

        <T> void requestSuccess(int code,T t);

        void requestError(int code);
    }

    interface Presenter extends BasePresenter {
        void getPhoneData(String phone, String key);
    }
}
