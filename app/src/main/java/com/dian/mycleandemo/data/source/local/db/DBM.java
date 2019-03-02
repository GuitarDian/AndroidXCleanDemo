package com.dian.mycleandemo.data.source.local.db;


import com.dian.mycleandemo.data.constant.LC;
import com.dian.mycleandemo.data.entity.DaoMaster;
import com.dian.mycleandemo.data.entity.DaoSession;
import com.dian.mycleandemo.data.entity.UserDao;
import com.dian.mycleandemo.presentation.base.BaseApp;
import com.dian.mycleandemo.presentation.util.Preconditions;

/**
 * Created by Dian on 2018/9/19.
 * 本地数据库管理
 */

public class DBM {

        private DBM(){}

        private static class Holder{
                static DaoMaster.DevOpenHelper devOpenHelper =
                        new DaoMaster.DevOpenHelper(BaseApp.getContext(), LC.DB_DEFAULT_NAME, null);
                static DaoSession daoSession =
                        new DaoMaster(devOpenHelper.getWritableDatabase()).newSession();
        }

        //-----------------创建各种Dao-------------------
        public static UserDao getUserDao(){
                return Preconditions.checkNotNull(Holder.daoSession).getUserDao();
        }

}
