package com.example.mvpdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mvpdemo.R;
import com.example.mvpdemo.presenter.contract.FindPhoneContract;
import com.example.mvpdemo.presenter.presenter.FindPhonePresenter;

public class MainActivity extends AppCompatActivity {

    private MainFragment mainFragment;
    private FindPhoneContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = new MainFragment();
        presenter = new FindPhonePresenter(this, mainFragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, mainFragment).commit();
    }
}
