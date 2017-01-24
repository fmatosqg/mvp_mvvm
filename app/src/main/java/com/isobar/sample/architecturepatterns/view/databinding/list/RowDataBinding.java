package com.isobar.sample.architecturepatterns.view.databinding.list;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.isobar.sample.architecturepatterns.model.Person;

/**
 * Created by fabio.goncalves on 24/01/2017.
 */

public class RowDataBinding extends BaseObservable {

    final private Person person;

    public RowDataBinding(Person person) {
        this.person = person;
    }

    @Bindable
    public Person getPerson() {
        return person;
    }

}
