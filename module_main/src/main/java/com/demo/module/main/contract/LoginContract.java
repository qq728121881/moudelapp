package com.demo.module.main.contract;

import com.demo.module.common.base.BaseView;
import com.demo.module.main.bean.LoginBean;

import retrofit2.Call;

/**
 * author : 郑振楠
 * date   : 2020/5/27
 */
public interface LoginContract {
    interface Model {
        Call<String> login(String username, String password);
    }

    interface View extends BaseView {

        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void onSuccess(String bean);

    }

    interface Presenter {
        void login(String username, String password);
    }
}
