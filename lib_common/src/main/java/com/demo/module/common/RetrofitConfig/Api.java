package com.demo.module.common.RetrofitConfig;

import com.demo.module.common.ServiceAPI.ConfigURL;
import com.demo.module.common.http.ApiService;

public class Api {
    private static ApiService apiService;
    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (Api.class) {
                if (apiService == null) {
                    apiService = RetrofitAPIManager.getInstance().getRetrofit(ConfigURL.API_URL).create(ApiService.class);
                }
            }
        }
        return apiService;
    }
}
