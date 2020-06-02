package com.demo.module.common.base;

import android.content.Context;
import android.text.TextUtils;
import com.demo.module.common.utils.NetworkUtils;
import com.demo.module.common.utils.ToastUtils;
import org.reactivestreams.Subscriber;
import java.net.SocketTimeoutException;
import retrofit2.HttpException;

/**
 * @author liuml
 * @explain 封装基础Subscriber
 * @time 2017/3/3 18:05
 */

public abstract class BaseSubscriber<T> implements Subscriber<T> {
    protected Context mContext;

    public BaseSubscriber(Context mContext) {
        this.mContext = mContext;
    }

    public BaseSubscriber() {
    }

    

     

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            HttpException e1 = (HttpException) e;

            int code = e1.code();
            onFail(code, ((HttpException) e).message());
        }else  if(e instanceof SocketTimeoutException){
            ToastUtils.showLongToast("请求超时,请稍后重试");
        }else {
            ToastUtils.showLongToast("服务器连接失败,请稍后重试");
        }
        
    }

    @Override
    public void onNext(T t) {

    }


    public static void onFail(int state, String message) {

        if (!NetworkUtils.isConnected()) {
           ToastUtils.showLongToast("网络错误，请检查网络后重试");
        } else if (!TextUtils.isEmpty(message)) {
            if (message.contains("timed out")) {
               ToastUtils.showLongToast("请求超时,请稍后重试");
            } else if (state == 500 || state == 502) {
               ToastUtils.showLongToast("服务异常,请稍后重试");
            } else if (message.contains("refused")) {
               ToastUtils.showLongToast("服务请求超时，请稍后重试");
            } else if (state == 0) {
               ToastUtils.showLongToast("数据异常,请稍后重试");
            } else if (state == 404) {
               ToastUtils.showLongToast("服务不存在,请稍后重试");
            } else if (state == 403) {
               ToastUtils.showLongToast("服务资源不可用,请稍后重试");
            } else if (state == 401) {
               ToastUtils.showLongToast("免密登录已过期，请重新登录");
            }
            else
               ToastUtils.showLongToast(message);
        }
    }

//    public abstract void onError(ExceptionHandle.ResponeThrowable e);
}

