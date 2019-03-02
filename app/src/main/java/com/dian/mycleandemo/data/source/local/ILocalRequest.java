package com.dian.mycleandemo.data.source.local;


import com.dian.mycleandemo.data.entity.User;

/**
 * Created by Dian on 2018/12/23
 * 本地数据接口
 */
public interface ILocalRequest {
    void saveUserInfo(User user);
}
