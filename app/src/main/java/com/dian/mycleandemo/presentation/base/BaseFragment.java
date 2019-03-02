package com.dian.mycleandemo.presentation.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Dian on 2019/2/9
 * 禁止预加载fragment
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    protected BasePresenter presenter;

    //Fragment的View加载完毕的标记
    private boolean isViewCreated;
    //Fragment对用户可见的标记
    private boolean isUIVisible;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflate(inflater, container);
        unbinder = ButterKnife.bind(this, rootView);
        presenter = genPresenter();
        if (presenter != null)
            //noinspection unchecked
            presenter.attachView(this);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        initInstanceState(savedInstanceState);
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isUIVisible = isVisibleToUser;
        if(isUIVisible) {
            lazyLoad();
        }
    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,
        // 并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            initData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;

        }
    }

    @Override
    public void onDestroyView() {
        if (presenter != null) {
            presenter.detach();
            presenter.detachView();
            presenter = null;
        }
        //页面销毁,恢复标记
        isViewCreated = false;
        isUIVisible = false;
        unbinder.unbind();
        super.onDestroyView();
    }

    public abstract View inflate(LayoutInflater inflater, ViewGroup container);

    public abstract void initInstanceState(Bundle savedInstanceState);
    public abstract void initData();

    protected abstract BasePresenter genPresenter();
}
