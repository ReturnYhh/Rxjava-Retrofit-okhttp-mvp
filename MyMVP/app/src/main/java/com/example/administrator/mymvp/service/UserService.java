package com.example.administrator.mymvp.service;


import com.example.administrator.mymvp.bean.BaseBean;
import com.example.administrator.mymvp.bean.LoginBean;
import com.example.administrator.mymvp.net.NetUrl;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 用户Service
 */
public interface UserService {
    @FormUrlEncoded
    @POST(NetUrl.LOGIN_URL)
    Observable<BaseBean<LoginBean>> doLogin(@Field("AppId") String appId,
                                            @Field("AppSecret") String appSecret,
                                            @Field("username") String username,
                                            @Field("password") String password
    );
}
