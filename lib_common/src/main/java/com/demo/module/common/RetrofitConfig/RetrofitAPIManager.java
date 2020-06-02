// (c)2016 Flipboard Inc, All Rights Reserved.

package com.demo.module.common.RetrofitConfig;



import android.text.TextUtils;

import com.demo.module.common.BuildConfig;
import com.demo.module.common.base.BaseApplication;
import com.demo.module.common.http.ApiService;
import com.demo.module.common.utils.ACache;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author liuml.
 * @explain Retorfit API管理器
 * @time 2017/3/2 15:39
 */
public class RetrofitAPIManager {
    private ApiService woNiuApi;
    private static RetrofitAPIManager instance;
//    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    private Retrofit retrofit;
    private Map<String, Retrofit> retrofitMap = new HashMap<>();
    public static final long TIME_OUT=10000;
  static ACache aCache=ACache.get(BaseApplication.getContext());
    //获取单例
    public static RetrofitAPIManager getInstance() {

        if (instance==null){
            synchronized (RetrofitAPIManager.class){
                if (instance==null){
                    instance=new RetrofitAPIManager();
                }
            }
        }
        return instance;
    }
    ScalarsConverterFactory scalarsConverterFactory = ScalarsConverterFactory.create();
    //构造方法私有
    private RetrofitAPIManager() {


    }
    public Retrofit getRetrofit(String baseUrl){

        if (retrofitMap.get(baseUrl) != null) return retrofitMap.get(baseUrl);
        //构建Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                //设置OKHttpClient为网络客户端
                .client(genericClient())

                //配置服务器路径
                .baseUrl(baseUrl)
                .addConverterFactory(scalarsConverterFactory)//配置转化库 字符串类型 的
//                //配置转化库，默认是Gson(返回参数不规范 要不然可以直接转换成实体类)
//                .addConverterFactory(gsonConverterFactory)
                //配置回调库，采用RxJava
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
        retrofitMap.put(baseUrl, retrofit);
        return retrofit;
    }
    //api
    public ApiService getApi() {
        //构建Retrofit
        if (woNiuApi == null) {
            woNiuApi = retrofit.create(ApiService.class);
        }
        return woNiuApi;
    }

 static String Token;
    public static OkHttpClient genericClient() {


        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (BuildConfig.DEBUG) {

                }
            }
        });

        loggingInterceptor.setLevel(level);

        //添加请求头
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Token = aCache.getAsString("Token");
                if(TextUtils.isEmpty(Token)){
                    Token="";
                }
                Request original = chain.request();

                Request request = original.newBuilder()
//                        .addHeader("userData", "json")
                        .header("pda_token",Token)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        };

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new LogInterceptor())
                .addInterceptor(interceptor)
                .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .build();

        return httpClient;
    }


}
