package com.dian.mycleandemo.presentation.rule;

/**
 * Created by Dian on 2018/12/23
 */
public interface LoginRule {
    interface V{
        void gotoMainActivity();
    }
    interface P{
        void login(String account, String password);
    }
}
