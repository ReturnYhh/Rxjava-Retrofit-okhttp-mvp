package com.example.administrator.mymvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.mymvp.bean.LoginBean;
import com.example.administrator.mymvp.presenter.UserPresenter;
import com.example.administrator.mymvp.view.LoginView;
import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity implements LoginView {
    private UserPresenter<MainActivity> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new UserPresenter<>(this, this);
        presenter.doLogin("qq358194685", "123456");
    }

    @Override
    public void loginSuccess(LoginBean bean) {
        Logger.d(bean);
    }

    @Override
    public void showMsg(String msg) {
        Logger.d(msg);
    }
}
