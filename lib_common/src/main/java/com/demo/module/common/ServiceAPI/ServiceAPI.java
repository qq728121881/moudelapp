package com.demo.module.common.ServiceAPI;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;

/**
 * Created by 郑振楠 on 2017/6/27.
 */

public interface ServiceAPI   {
    /**
     * 登录接口
     */
    @FormUrlEncoded
    @PUT("login?")
    Call<String> login(@Field("account") String account,
                       @Field("password") String password);


}
