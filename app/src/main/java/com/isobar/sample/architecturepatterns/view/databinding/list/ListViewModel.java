package com.isobar.sample.architecturepatterns.view.databinding.list;

/**
 * Created by fabio.goncalves on 23/01/2017.
 */

public class ListViewModel {

    private final String title;

    private boolean isInProgress;
    private boolean isError;
    private boolean isShowPlaceholder;
    private boolean isShowList;

    public ListViewModel(){

        isInProgress = false;
        isShowPlaceholder = true;
        isError = false;
        isShowList = false;

        title = "Data Binding";
    }

    public String getTitle() {
        return title;
    }

    public boolean isInProgress() {
        return isInProgress;
    }

    public void setInProgress(boolean inProgress) {
        isInProgress = inProgress;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public boolean isShowPlaceholder() {
        return isShowPlaceholder;
    }

    public void setShowPlaceholder(boolean showPlaceholder) {
        isShowPlaceholder = showPlaceholder;
    }

    public boolean isShowList() {
        return isShowList;
    }

    public void setShowList(boolean showList) {
        isShowList = showList;
    }
}
