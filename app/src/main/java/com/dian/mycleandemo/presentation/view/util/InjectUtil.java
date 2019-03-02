package com.dian.mycleandemo.presentation.view.util;


import android.content.Context;

import com.dian.mycleandemo.data.source.Repository;
import com.dian.mycleandemo.data.source.local.LocalSource;
import com.dian.mycleandemo.data.source.remote.RemoteSource;
import com.dian.mycleandemo.domain.usercase.LoginUseCase;
import com.dian.mycleandemo.presentation.util.Preconditions;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Dian on 2018/11/26.
 * 初始化UseCase
 */

public class InjectUtil {

    public static Repository provideDianRepository(Context context) {
        Preconditions.checkNotNull(context);
        return Repository.getInstance(LocalSource.getInstance(context),
                RemoteSource.getInstance(context));
    }

    public static LoginUseCase provideLoginUseCase(Context context) {
        Preconditions.checkNotNull(context);
        return new LoginUseCase(Schedulers.io(), AndroidSchedulers.mainThread(), provideDianRepository(context));
    }


}
