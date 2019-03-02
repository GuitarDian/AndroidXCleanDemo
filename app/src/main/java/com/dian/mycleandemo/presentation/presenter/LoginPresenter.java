package com.dian.mycleandemo.presentation.presenter;

import com.dian.mycleandemo.data.entity.User;
import com.dian.mycleandemo.domain.usercase.LoginUseCase;
import com.dian.mycleandemo.presentation.base.BasePresenter;
import com.dian.mycleandemo.presentation.rule.LoginRule;
import com.dian.mycleandemo.presentation.util.Preconditions;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Dian on 2018/12/23
 */
public class LoginPresenter extends BasePresenter<LoginRule.V> implements LoginRule.P{

    private final LoginUseCase loginUseCase;

    public LoginPresenter(LoginUseCase loginUseCase) {
        this.loginUseCase = Preconditions.checkNotNull(loginUseCase, "loginUseCase is null");
    }

    @Override
    protected void detach() {
        loginUseCase.dispose();
    }

    @Override
    public void login(String account, String password) {
        loginUseCase.execute(new LoginObserver(), new LoginUseCase.RequestValue(account, password));
    }

    private final class LoginObserver extends DisposableObserver<User> {

        @Override
        public void onNext(User user) {
            loginUseCase.saveUser(user);
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    }
}
