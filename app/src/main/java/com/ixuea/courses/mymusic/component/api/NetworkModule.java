package com.ixuea.courses.mymusic.component.api;

import com.ixuea.courses.mymusic.AppContext;
import com.ixuea.courses.mymusic.Config.Config;
import com.ixuea.courses.mymusic.util.JSONUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkModule {
    /**
     * 提供OkHttpClient
     */
    public static OkHttpClient provideOkHttpClient() {
        //初始化okhttp
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();

        //配置缓存
        Cache cache = new Cache(AppContext.getInstance().getCacheDir(), Config.NETWORK_CACHE_SIZE);
        okhttpClientBuilder.cache(cache);

        okhttpClientBuilder.connectTimeout(10, TimeUnit.SECONDS) //连接超时时间
                .writeTimeout(10, TimeUnit.SECONDS) //写，也就是将数据发送到服务端超时时间
                .readTimeout(10, TimeUnit.SECONDS); //读，将服务端的数据下载到本地

        if (Config.DEBUG) {
            //调试模式

            //创建okhttp日志拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

            //设置日志等级
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BASIC);

            //添加到网络框架中
            okhttpClientBuilder.addInterceptor(loggingInterceptor);
        }

        return okhttpClientBuilder.build();
    }

    /**
     * 提供Retrofit实例
     *
     * @param okHttpClient
     * @return
     */
    public static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()

                //让retrofit使用okhttp
                .client(okHttpClient)

                //api地址
                .baseUrl(Config.ENDPOINT)

                //适配rxjava
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())

                //使用gson解析json
                //包括请求参数和响应
                .addConverterFactory(GsonConverterFactory.create(JSONUtil.createGson()))

                //创建retrofit
                .build();
    }

}
