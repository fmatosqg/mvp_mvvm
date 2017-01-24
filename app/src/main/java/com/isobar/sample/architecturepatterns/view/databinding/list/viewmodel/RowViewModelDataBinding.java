package com.isobar.sample.architecturepatterns.view.databinding.list.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.isobar.sample.architecturepatterns.model.Person;

/**
 * Created by fabio.goncalves on 24/01/2017.
 */

public class RowViewModelDataBinding extends BaseObservable {

    final private Person person;

    public RowViewModelDataBinding(Person person) {
        this.person = person;
    }

    @Bindable
    public Person getPerson() {
        return person;
    }

}
