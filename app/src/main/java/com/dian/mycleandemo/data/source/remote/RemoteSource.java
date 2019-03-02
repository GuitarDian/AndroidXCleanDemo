package com.dian.mycleandemo.data.source.remote;

import android.content.Context;

import com.dian.mycleandemo.data.entity.User;
import com.dian.mycleandemo.data.exception.NetworkException;
import com.dian.mycleandemo.data.source.remote.net.DianHttp;
import com.dian.mycleandemo.data.source.remote.net.request.LoginRequest;
import com.dian.mycleandemo.presentation.util.SysUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Dian on 2018/12/23
 * 网络数据请求
 */
public class RemoteSource implements IRemoteRequest {

    private Context context;
    private CompositeDisposable compositeDisposable;
    private static RemoteSource INSTANCE;

    private RemoteSource(Context context) {
        this.context = context;
        compositeDisposable = new CompositeDisposable();
    }

    public static RemoteSource getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = new RemoteSource(context);
        return INSTANCE;
    }

    @Override
    public Observable<User> getUser(final String loginAccount, final String loginPsw) {
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(final ObservableEmitter<User> e) throws Exception {
                if (SysUtil.isInternetConnection()) {
                    compositeDisposable.add(DianHttp.build().getApi().getUser(
                            new LoginRequest(loginAccount, loginPsw).getRequestBody())
                            .subscribe(new Consumer<User>() {
                                @Override
                                public void accept(User user) throws Exception {
                                    //TODO 做你的操作
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    e.onError(throwable);
                                }
                            }));
                }else {
                    e.onError(new NetworkException("网络不可用"));
                }
            }
        });
    }

}
