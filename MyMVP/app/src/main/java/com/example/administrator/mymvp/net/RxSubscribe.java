package com.example.administrator.mymvp.net;

import android.app.ProgressDialog;
import android.content.Context;


import com.example.administrator.mymvp.util.CommonUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * 回调封装
 */
public abstract class RxSubscribe<T> extends Subscriber<T> {
    private Context mContext;
    private ProgressDialog mDialog;
    //是否显示对话框
    private boolean mShowDialog;

    public RxSubscribe(Context context, boolean showDialog) {
        this.mContext = context;
        this.mShowDialog = showDialog;
    }

    @Override
    public void onCompleted() {
        if (!isUnsubscribed()) {
            unsubscribe();
        }
        if (mDialog != null&&mShowDialog)
            mDialog.dismiss();
        mDialog = null;
    }

    @Override
    public void onStart() {
        if (!CommonUtil.isNetWorkConnected(mContext)) {
            if (!isUnsubscribed()) {
                unsubscribe();
            }
            _onError("网络不可用");
        } else {
            if (mDialog == null && mShowDialog) {
                mDialog = new ProgressDialog(mContext);
                mDialog.setMessage("正在加载中...");
                mDialog.show();
            }
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (!CommonUtil.isNetWorkConnected(mContext)) {
            _onError("网络不可用");
            //SocketTimeoutException:服务器响应超时
            //ConnectException:服务器请求超时
        } else if (e instanceof SocketTimeoutException || e instanceof ConnectException) {
            _onError("请求超时,请稍后再试...");
        } else if (e instanceof HttpException) {
            _onError("服务器异常，请稍后再试...");
        } else {
            _onError(e.getMessage());
        }
        if (mDialog != null && mShowDialog)
            mDialog.dismiss();
        mDialog = null;
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

}
