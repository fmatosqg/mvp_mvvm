package com.isobar.sample.architecturepatterns.view.mvp.interfaces;

/**
 * Created by fabio.goncalves on 13/01/2017.
 */
public interface MvpPresenter<T> {
    void attachView(T view);
    boolean isViewAttached();
    void detachView();
}
