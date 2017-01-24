package com.isobar.sample.architecturepatterns.view.databinding.list;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingConversion;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.isobar.sample.architecturepatterns.BR;
import com.isobar.sample.architecturepatterns.bus.EventBus;
import com.isobar.sample.architecturepatterns.bus.NewPersonEvent;
import com.isobar.sample.architecturepatterns.model.Person;
import com.isobar.sample.architecturepatterns.model.PersonDao;
import com.isobar.sample.architecturepatterns.model.RandomFailureException;
import com.squareup.otto.Subscribe;

import java.util.Collection;

/**
 * Created by fabio.goncalves on 23/01/2017.
 */

public class ListViewModelDataBinding extends BaseObservable {

    private static final String TAG = ListViewModelDataBinding.class.getSimpleName();

    private final String title;

    private boolean isInProgress;
    private boolean isError;
    private boolean isShowPlaceholder;
    private boolean isShowList;
    private AsyncTask<Void, Void, Collection<Person>> task;

    public ListViewModelDataBinding() {

        isInProgress = false;
        isShowPlaceholder = true;
        isError = false;
        isShowList = false;

        title = "Data Binding";

        EventBus.getInstance().register(this);
        loadList();
    }

    public void destroy() {
        EventBus.getInstance().unregister(this);
    }

    private void loadList() {

        Log.i(TAG, "Load people list");

        task = new AsyncTask<Void, Void, Collection<Person>>() {

            private boolean isDaoFailed = false;

            @Override
            protected Collection<Person> doInBackground(Void... voids) {
                Collection<Person> list = null;
                setError(false);
                setInProgress(true);
                setShowPlaceholder(false);
                try {
                    list = PersonDao.getInstance().queryAll();

                } catch (RandomFailureException e) {
                    Log.i(TAG, e.getMessage());
                    isDaoFailed = true;
                }
                return list;
            }

            @Override
            protected void onPostExecute(Collection<Person> peopleList) {

                setInProgress(false);

                if (peopleList != null && peopleList.size() > 0) {
                    setShowPlaceholder(false);
                    setShowList(true);
                } else {
                    setShowPlaceholder(true);
                    setShowList(false);
                }

                setError(isDaoFailed);

                //adapter.setList(peopleList)

            }
        };

        task.execute();
    }

    public String getTitle() {
        return title;
    }

    @BindingConversion
    public static int convertBooleanToVisibility(boolean visible) {
        return visible ? View.VISIBLE : View.GONE;
    }

    @Bindable
    public boolean isInProgress() {
        return isInProgress;
    }

    public void setInProgress(boolean inProgress) {
        isInProgress = inProgress;
        notifyPropertyChanged(BR.inProgress);
    }

    @Bindable
    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
        notifyPropertyChanged(BR.error);
    }

    @Bindable
    public boolean isShowPlaceholder() {
        return isShowPlaceholder;
    }

    public void setShowPlaceholder(boolean showPlaceholder) {
        isShowPlaceholder = showPlaceholder;
        notifyPropertyChanged(BR.showPlaceholder);
    }

    @Bindable
    public boolean isShowList() {
        return isShowList;
    }

    public void setShowList(boolean showList) {
        isShowList = showList;
        notifyPropertyChanged(BR.showList);
    }

    @Subscribe
    public void onNewPersonEvent(NewPersonEvent event) {
        loadList();
    }
}
