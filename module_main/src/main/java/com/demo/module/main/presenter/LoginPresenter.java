package com.demo.module.main.presenter;

import com.demo.module.common.base.BasePresenter;
import com.demo.module.main.bean.LoginBean;
import com.demo.module.main.contract.LoginContract;
import com.demo.module.main.model.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * author : 郑振楠
 * date   : 2020/5/27
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

   private LoginModel loginModel;

    public LoginPresenter( ) {
        loginModel = new LoginModel();
    }

    @Override
    public void login(String username, String password) {


        loginModel.login(username,password).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String body = response.body();
                mView.onSuccess(response.body());
                mView.hideLoading();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                String message = t.getMessage();
            }
        });


    }
}
