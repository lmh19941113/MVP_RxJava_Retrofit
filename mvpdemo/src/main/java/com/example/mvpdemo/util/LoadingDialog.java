package com.example.mvpdemo.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.annotation.StringRes;

public class LoadingDialog {
    private ProgressDialog progressDialog;
    private Activity context;


    public LoadingDialog(Activity context) {
        this.context = context;
    }

    public ProgressDialog setMessage(String text) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(text);
        return progressDialog;
    }

    public ProgressDialog setMessage(@StringRes int strRes) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(UiUtils.getText(strRes));
        return progressDialog;
    }

    public void show() {
        progressDialog.show();
    }

//    public void show() {
//        if (progressDialog == null) {
//            progressDialog = new android.app.ProgressDialog(context);
//            progressDialog.setMessage("正在加载数据，请稍后...");
//            progressDialog.setCancelable(false);//设置进度条是否可以按退回键取消
////            //设置点击进度对话框外的区域对话框不消失
//            progressDialog.setCanceledOnTouchOutside(false);
//            progressDialog.show();
//        }
//    }


    public void dismiss() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
