package com.isobar.sample.architecturepatterns.view.mvp.interfaces;

import java.lang.ref.WeakReference;

/**
 * Created by fabio.goncalves on 19/01/2017.
 */

public abstract class MvpPresenterImpl<T> implements MvpPresenter<T> {

    private WeakReference<T> weakView;
    private boolean isAttached;

    @Override
    public void attachView(T view) {
        weakView = new WeakReference<T>(view);
        isAttached = true;
    }

    @Override
    public boolean isViewAttached() {
        return isAttached && weakView.get() != null;
    }

    @Override
    public void detachView() {
        weakView = null;
        isAttached = false;
    }

    public T getView() {

        return weakView.get();
    }

}
