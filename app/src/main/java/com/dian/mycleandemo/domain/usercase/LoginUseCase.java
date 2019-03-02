package com.dian.mycleandemo.domain.usercase;

import com.dian.mycleandemo.data.entity.User;
import com.dian.mycleandemo.data.source.Repository;
import com.dian.mycleandemo.domain.BaseUseCase;
import com.dian.mycleandemo.presentation.util.Preconditions;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by Dian on 2018/12/25
 */
public class LoginUseCase extends BaseUseCase<LoginUseCase.RequestValue, User> {

    private final Repository repository;

    public LoginUseCase(Scheduler observerThread, Scheduler subcriberThread, Repository repository) {
        super(observerThread, subcriberThread);
        this.repository = repository;
    }

    @Override
    protected Observable<User> buildUseCaseObservable(RequestValue request) {
        return repository.getUser(request.account, request.password);
    }


    public static final class RequestValue {
        final String account;
        final String password;

        public RequestValue(String account, String password) {
            this.account = Preconditions.checkNotNull(account);
            this.password = Preconditions.checkNotNull(password);
        }
    }

    public void saveUser(User user) {
        repository.saveUser(user);
    }
}
