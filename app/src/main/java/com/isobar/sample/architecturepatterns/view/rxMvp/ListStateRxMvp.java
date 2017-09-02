package com.isobar.sample.architecturepatterns.view.rxMvp;

import com.isobar.sample.architecturepatterns.model.Person;

import java.util.Collection;

/**
 * Created by fmatos on 2/09/2017.
 */

public class ListStateRxMvp {

    final boolean showList;
    final boolean showSpinner;
    final boolean showPlaceholder;
    final boolean showError;

    final String title = "RxMvp";
    private Collection<Person> personList;

    public ListStateRxMvp() {
        this(false, false, true, false);
    }

    public ListStateRxMvp(boolean showList, boolean showSpinner, boolean showPlaceholder, boolean showError) {
        this.showList = showList;
        this.showSpinner = showSpinner;
        this.showPlaceholder = showPlaceholder;
        this.showError = showError;
    }

    public void setPersonList(Collection<Person> personList) {
        this.personList = personList;
    }

    public Collection<Person> getPersonList() {
        return personList;
    }
}
