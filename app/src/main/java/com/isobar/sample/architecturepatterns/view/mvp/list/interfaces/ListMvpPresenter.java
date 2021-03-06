package com.isobar.sample.architecturepatterns.view.mvp.list.interfaces;

import android.support.v4.app.FragmentManager;

import com.isobar.sample.architecturepatterns.view.mvp.interfaces.MvpPresenter;

/**
 * Created by fabio.goncalves on 13/01/2017.
 */
public interface ListMvpPresenter extends MvpPresenter<ListMvpView> {

    // view calls these
    void newUser(FragmentManager activity);
    void loadUserList();
}
