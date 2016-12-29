package com.example.administrator.mymvp.model;


import com.example.administrator.mymvp.bean.LoginBean;
import com.example.administrator.mymvp.net.NetHttpApi;
import com.example.administrator.mymvp.net.NetUrl;
import com.example.administrator.mymvp.net.RxHelper;
import com.example.administrator.mymvp.net.RxSubscribe;
import com.example.administrator.mymvp.service.UserService;

public class UserModel {
    private UserService mService;

    public UserModel() {
        mService = NetHttpApi.getInstance().getService(UserService.class);
    }

    public void doLogin(String username, String password, RxSubscribe<LoginBean> subscribe) {
        mService.doLogin(NetUrl.APP_ID, NetUrl.APP_SECRET, username, password)
                .compose(RxHelper.<LoginBean>handleResult())
                .subscribe(subscribe);
    }
}
