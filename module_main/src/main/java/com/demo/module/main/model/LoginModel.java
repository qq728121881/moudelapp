package com.demo.module.main.model;

import com.demo.module.common.RetrofitConfig.ServiceFactory;
import com.demo.module.main.contract.LoginContract;
import retrofit2.Call;

/**
 * author : 郑振楠
 * date   : 2020/5/27
 */
public class LoginModel implements LoginContract.Model {

    @Override
    public Call<String> login(String username, String password) {

        return ServiceFactory.getBusinessApi().login(username,password );
    }
}
