package com.example.mvpdemo.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by admin on 2016/9/3.
 */
public class MyApplication extends Application {
    private static final String TAG = "com.jobnew.helloHan.OnLineApplication";
    private static MyApplication application;
    private static Handler handler;
    private static int mainTid;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        // 获取当前线程id
        mainTid = android.os.Process.myTid();
        handler = new Handler();
    }

    public static Context getApplication() {
        return application;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainTid() {
        return mainTid;
    }
}
