package com.example.administrator.mymvp.presenter;

import android.content.Context;

import com.example.administrator.mymvp.bean.LoginBean;
import com.example.administrator.mymvp.model.UserModel;
import com.example.administrator.mymvp.net.RxSubscribe;
import com.example.administrator.mymvp.view.BaseView;
import com.example.administrator.mymvp.view.LoginView;

public class UserPresenter<T extends BaseView> {
    private UserModel mUserModel;
    private Context mContext;
    private LoginView mLoginView;
    private T mView;

    public UserPresenter(Context context, T view) {
        mUserModel = new UserModel();
        this.mView = view;
        this.mContext = context;
    }

    public void doLogin(String username, String password) {
        mUserModel.doLogin(username, password,
                new RxSubscribe<LoginBean>(mContext, true) {
                    @Override
                    protected void _onNext(LoginBean loginBean) {
                        mLoginView = (LoginView) mView;
                        mLoginView.loginSuccess(loginBean);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.showMsg(message);
                    }
                });
    }
}
