package com.dian.mycleandemo.data.source;

import com.dian.mycleandemo.data.entity.User;
import com.dian.mycleandemo.data.source.local.ILocalRequest;
import com.dian.mycleandemo.data.source.remote.IRemoteRequest;
import com.dian.mycleandemo.presentation.util.Preconditions;

import io.reactivex.Observable;

/**
 * Created by Dian on 2018/12/23
 * 所有数据请求
 */
public class Repository implements IRepository {

    private static Repository INSTANCE;
    private ILocalRequest localRequest;
    private IRemoteRequest remoteRequest;

    private Repository(ILocalRequest local, IRemoteRequest remote) {
        localRequest = Preconditions.checkNotNull(local, "local source is null");
        remoteRequest = Preconditions.checkNotNull(remote, "remote source is null");
    }

    public static Repository getInstance(ILocalRequest local, IRemoteRequest remote) {
        if (INSTANCE == null)
            INSTANCE = new Repository(local, remote);
        return INSTANCE;
    }

    @Override
    public Observable<User> getUser(String loginAccount, String loginPsw) {
        return remoteRequest.getUser(loginAccount, loginPsw);
    }

    @Override
    public void saveUser(User user) {
        localRequest.saveUserInfo(user);
    }
}
