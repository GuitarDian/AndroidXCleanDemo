package com.dian.mycleandemo.presentation.base;

import java.lang.ref.WeakReference;

/**
 * Created by Dian on 2018/9/19.
 */

public abstract class BasePresenter<T> {

    WeakReference<T> viewReference;

    public void attachView(T view) {
        viewReference = new WeakReference<>(view);
    }

    public void detachView() {
        if (viewReference != null) {
            viewReference.clear();
            viewReference = null;
        }
    }

    public boolean isViewAttached() {
        return viewReference != null && viewReference.get() != null;
    }

    public T getView() {
        if (!isViewAttached())
            return null;
        return viewReference.get();
    }

    protected abstract void detach();

}
