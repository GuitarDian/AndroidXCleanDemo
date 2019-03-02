package com.dian.mycleandemo.data.source.remote.net;


import com.dian.mycleandemo.data.entity.User;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Dian on 2018/12/24
 * retrofit接口
 */
public interface IDianApi {

    @POST("service")
    Observable<User> getUser(@Body RequestBody requestBody);
}
