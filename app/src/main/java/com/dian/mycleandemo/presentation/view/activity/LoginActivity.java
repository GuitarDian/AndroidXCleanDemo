package com.dian.mycleandemo.presentation.view.activity;

import android.view.View;
import android.widget.EditText;

import com.dian.mycleandemo.R;
import com.dian.mycleandemo.presentation.base.BaseActivity;
import com.dian.mycleandemo.presentation.base.BaseApp;
import com.dian.mycleandemo.presentation.base.BasePresenter;
import com.dian.mycleandemo.presentation.presenter.LoginPresenter;
import com.dian.mycleandemo.presentation.rule.LoginRule;
import com.dian.mycleandemo.presentation.util.Preconditions;
import com.dian.mycleandemo.presentation.util.TextUtil;
import com.dian.mycleandemo.presentation.util.ToastUtil;
import com.dian.mycleandemo.presentation.view.util.InjectUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Dian on 2018/12/2s3
 * 登录界面
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_account)
    EditText etAccount;

    @BindView(R.id.et_password)
    EditText etPassword;

    private LoginRule.P presenter;

    @Override
    protected void bindView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initData() { }

    @Override
    protected BasePresenter genPresenter() {
        presenter = new LoginPresenter(InjectUtil.provideLoginUseCase(BaseApp.getContext()));
        return (BasePresenter) presenter;
    }

    @OnClick({R.id.btn_login})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_login:
                String account = etAccount.getText().toString();
                String password = etPassword.getText().toString();
                if(TextUtil.isEmpty(account)|| TextUtil.isEmpty(password)){
                    ToastUtil.showShort(BaseApp.getContext(), "请填写完整的信息");
                    return;
                }
                Preconditions.checkNotNull(presenter).login(account, password);
                break;
        }
    }
}
