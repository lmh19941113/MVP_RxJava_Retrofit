package com.example.mvpdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpdemo.R;
import com.example.mvpdemo.model.bean.PhoneBean;
import com.example.mvpdemo.presenter.contract.FindPhoneContract;
import com.example.mvpdemo.presenter.presenter.FindPhonePresenter;
import com.example.mvpdemo.util.LoadingDialog;

/**
 * Created by admin on 2016/9/3.
 */
public class MainFragment extends Fragment implements FindPhoneContract.View, View.OnClickListener {

    private FindPhoneContract.Presenter presenter;
    private LoadingDialog loadingDialog;


    private View view;
    private TextView showResult;
    private EditText context;
    private Button btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, null);
        showResult = (TextView) view.findViewById(R.id.show);
        context = (EditText) view.findViewById(R.id.test);
        btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(this);
        loadingDialog = new LoadingDialog(getActivity());
        return view;
    }

    @Override
    public void loading(int what) {
        loadingDialog.setMessage("正在加载数据，请稍后...").show();
    }

    @Override
    public void dissLoading() {
        loadingDialog.dismiss();
    }

    @Override
    public <T> void requestSuccess(int code, T t) {
        if (t instanceof PhoneBean.ResultBean) {
            Toast.makeText(getActivity(), "获取数据成功", Toast.LENGTH_SHORT).show();
            PhoneBean.ResultBean resultBean = (PhoneBean.ResultBean) t;
            showResult.setText("电话号码：" + resultBean.mobilenumber + "\n类型：" + resultBean.mobiletype +
                    "\n地方：" + resultBean.mobilearea);
        }
    }

    @Override
    public void requestError(int code) {
        Toast.makeText(getActivity(), "数据获取失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(FindPhoneContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.unSubscriber();
        }
    }

    private final static String key = "930d2c58620d47e4894a75e9dd8db352";

    @Override
    public void onClick(View view) {
        String str = context.getText().toString().trim();
        if (!TextUtils.isEmpty(str)) {
            if (presenter == null) {//防止系统回收掉presenter对象，从而报空指针异常
                presenter = new FindPhonePresenter(getActivity(), this);
            }
            presenter.getPhoneData(str, key);
        }
    }
}
