package com.isobar.sample.architecturepatterns.view.databinding.list;

import android.view.View;

/**
 * Created by fabio.goncalves on 23/01/2017.
 */

public class ListViewModelDataBinding {

    private static final String TAG = ListViewModelDataBinding.class.getSimpleName();

    private final String title;

    private boolean isInProgress;
    private boolean isError;
    private boolean isShowPlaceholder;
    private boolean isShowList;

    public ListViewModelDataBinding() {

        isInProgress = false;
        isShowPlaceholder = true;
        isError = false;
        isShowList = false;

        title = "Data Binding";
    }

    public String getTitle() {
        return title;
    }

    public int isInProgress() {
        return visibility(isInProgress);
    }

    public void setInProgress(boolean inProgress) {
        isInProgress = inProgress;
    }

    public int isError() {
        return visibility(isError);
    }

    private int visibility(boolean isVisibile) {
        return isVisibile ? View.VISIBLE : View.GONE;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public int isShowPlaceholder() {
        return visibility(isShowPlaceholder);
    }

    public void setShowPlaceholder(boolean showPlaceholder) {
        isShowPlaceholder = showPlaceholder;
    }

    public int isShowList() {
        return visibility(isShowList);
    }

    public void setShowList(boolean showList) {
        isShowList = showList;
    }

}
