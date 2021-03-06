package com.isobar.sample.architecturepatterns.view.mvp.list.interfaces;

import com.isobar.sample.architecturepatterns.view.mvp.interfaces.MvpView;

/**
 * Created by fabio.goncalves on 13/01/2017.
 */
public interface ListMvpView extends MvpView {

    void showList();
    void showSpinner();
    void showPlaceholder();
    void showError(boolean isShowError);
}
