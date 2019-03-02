package com.dian.mycleandemo.data.source;


import com.dian.mycleandemo.data.entity.User;

import io.reactivex.Observable;

/**
 * Created by Dian on 2018/12/23
 * 所有数据请求接口
 */
public interface IRepository {
    Observable<User> getUser(String loginAccount, String loginPsw);

    void saveUser(User user);
}
