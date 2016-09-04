package com.example.mvpdemo.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.*;

import com.example.mvpdemo.application.MyApplication;

public class UiUtils {


    private UiUtils() {
        throw new AssertionError("无法实例化该类");
    }

    /**
     * 获取到字符数组
     *
     * @param tabNames 字符数组的id
     */
    public static String[] getStringArray(@ArrayRes int tabNames) {
        return getResource().getStringArray(tabNames);
    }

    //这里采用资源注解，如传递参数与资源注解不符合则会报错
    public static int getColor(@ColorRes int color) {
        return getResource().getColor(color);
    }

    @NonNull //当返回值为null时会出现警告
    public static String getText(@StringRes int str) {
        return getResource().getString(str);
    }

    //这里采用资源注解，如传递参数与资源注解不符合则会报错
    public static Drawable getDrawable(@DrawableRes int drawable) {
        return getResource().getDrawable(drawable);
    }

    public static Resources getResource() {
        return MyApplication.getApplication().getResources();
    }

    public static Context getContext() {
        return MyApplication.getApplication();
    }

    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * px转换dip
     */

    public static int px2dip(int px) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static void runOnUiThread(Runnable runnable) {
        //判断当前线程是否是主线程（将当前线程的id与主线程所在的id进行判断）
        if (android.os.Process.myTid() == MyApplication.getMainTid()) {
            runnable.run();
        } else {
            // 放在主线程里面运行
            MyApplication.getHandler().post(runnable);
        }
    }
}
