package com.isobar.sample.architecturepatterns.view.mvp.list.interfaces;

import com.isobar.sample.architecturepatterns.model.Person;
import com.isobar.sample.architecturepatterns.view.mvp.interfaces.MvpView;

import java.util.Collection;

/**
 * Created by fabio.goncalves on 13/01/2017.
 */
public interface ListMvpView extends MvpView {

    void showList(Collection<Person> peopleList);
    void showSpinner();
    void showPlaceholder();
}
