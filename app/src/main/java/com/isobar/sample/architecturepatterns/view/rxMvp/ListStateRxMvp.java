package com.isobar.sample.architecturepatterns.view.rxMvp;

/**
 * Created by fmatos on 2/09/2017.
 */

public class ListStateRxMvp {

    final boolean showList;
    final boolean showSpinner;
    final boolean showPlaceholder;
    final boolean showError;

    public ListStateRxMvp() {
        this(false, false, true, false);
    }

    public ListStateRxMvp(boolean showList, boolean showSpinner, boolean showPlaceholder, boolean showError) {
        this.showList = showList;
        this.showSpinner = showSpinner;
        this.showPlaceholder = showPlaceholder;
        this.showError = showError;
    }

}
