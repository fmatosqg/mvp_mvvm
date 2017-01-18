package com.isobar.sample.architecturepatterns.view.mvp.list;

import com.isobar.sample.architecturepatterns.view.mvp.interfaces.MvpPresenter;

/**
 * Created by fabio.goncalves on 13/01/2017.
 */
public interface ListMvpPresenter extends MvpPresenter {

    void editUser();
    void newUser();
}
