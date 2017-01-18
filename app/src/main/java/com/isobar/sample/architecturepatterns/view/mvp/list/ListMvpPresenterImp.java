package com.isobar.sample.architecturepatterns.view.mvp.list;

import com.isobar.sample.architecturepatterns.view.mvp.interfaces.MvpView;

import java.lang.ref.WeakReference;

/**
 * Created by fabio.goncalves on 13/01/2017.
 */
public class ListMvpPresenterImp implements ListMvpPresenter {

    private WeakReference<MvpView> view;
    @Override
    public void editUser() {

    }

    @Override
    public void newUser() {

    }

    @Override
    public void attachView(MvpView view) {

//        view = (MvpView) new WeakReference<MvpView>(view);



    }
}
