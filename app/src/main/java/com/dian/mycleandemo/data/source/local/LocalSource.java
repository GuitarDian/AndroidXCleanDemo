package com.dian.mycleandemo.data.source.local;

import android.content.Context;

import com.dian.mycleandemo.data.entity.User;
import com.dian.mycleandemo.data.source.local.db.DBM;

/**
 * Created by Dian on 2018/12/23
 * 本地数据请求
 */
public class LocalSource implements ILocalRequest{

    private Context context;
    private static LocalSource INSTANCE;

    public static LocalSource getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = new LocalSource(context);
        return INSTANCE;
    }

    public LocalSource(Context context) {
        this.context = context;
    }

    @Override
    public void saveUserInfo(User user) {
        DBM.getUserDao().insertOrReplace(user);
    }
}
