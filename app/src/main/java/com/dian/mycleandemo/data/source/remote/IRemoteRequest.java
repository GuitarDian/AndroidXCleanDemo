package com.dian.mycleandemo.data.source.remote;


import com.dian.mycleandemo.data.entity.User;

import io.reactivex.Observable;

/**
 * Created by Dian on 2018/12/23
 * 网络请求接口
 */
public interface IRemoteRequest {
    Observable<User> getUser(String loginAccount, String loginPsw);
}
