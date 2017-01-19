package com.isobar.sample.architecturepatterns.view.mvp.list.interfaces;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.isobar.sample.architecturepatterns.view.mvp.interfaces.MvpPresenter;

/**
 * Created by fabio.goncalves on 13/01/2017.
 */
public interface ListMvpPresenter extends MvpPresenter<ListMvpView> {

    // view calls these
    void editUser(FragmentManager activity);
    void newUser(FragmentManager activity);
    void loadUserList();
}
