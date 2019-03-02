package com.dian.mycleandemo.presentation.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Dian on 2018/9/19.
 */
public abstract class BaseApp extends Application {

    private static BaseApp INSTANCE;
    public static BaseApp getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        init();
    }

    protected abstract void init();

    public static Context getContext() {
        return INSTANCE.getApplicationContext();
    }

}
