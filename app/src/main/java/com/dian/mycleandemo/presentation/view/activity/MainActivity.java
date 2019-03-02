package com.dian.mycleandemo.presentation.view.activity;

import com.dian.mycleandemo.R;
import com.dian.mycleandemo.presentation.base.BaseActivity;
import com.dian.mycleandemo.presentation.base.BasePresenter;

public class MainActivity extends BaseActivity {


    @Override
    protected void bindView(){
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter genPresenter() {
        return null;
    }

}
