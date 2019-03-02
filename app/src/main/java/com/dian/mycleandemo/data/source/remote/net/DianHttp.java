package com.dian.mycleandemo.data.source.remote.net;

import android.os.Environment;

import com.dian.mycleandemo.data.constant.NC;
import com.dian.mycleandemo.data.source.remote.net.converter.GsonConvertFactory;

import java.io.File;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Dian on 2018/11/27.
 * retrofit基本设置
 */

public class DianHttp {

    private IDianApi iDianApi;

    public DianHttp() {
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(NC.BASE_URL)
                .addConverterFactory(GsonConvertFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createClient())
                .build();
        iDianApi = retrofit2.create(IDianApi.class);
    }

    private OkHttpClient createClient() {
        File sdcache = Environment.getDownloadCacheDirectory();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        builder.cookieJar(new JavaNetCookieJar(cookieManager));
        return builder.build();
    }

    private static class HttpHolder {
        private static final DianHttp INSTANCE = new DianHttp();
    }

    public static DianHttp build() {
        return HttpHolder.INSTANCE;
    }

    public IDianApi getApi() {
        return iDianApi;
    }

}
