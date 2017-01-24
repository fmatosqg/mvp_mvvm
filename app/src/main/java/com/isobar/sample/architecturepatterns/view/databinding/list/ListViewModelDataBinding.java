package com.isobar.sample.architecturepatterns.view.databinding.list;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingConversion;
import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.isobar.sample.architecturepatterns.*;
import com.isobar.sample.architecturepatterns.bus.EventBus;
import com.isobar.sample.architecturepatterns.bus.NewPersonEvent;
import com.isobar.sample.architecturepatterns.model.Person;
import com.isobar.sample.architecturepatterns.model.PersonDao;
import com.isobar.sample.architecturepatterns.model.RandomFailureException;
import com.isobar.sample.architecturepatterns.view.databinding.util.RecyclerConfiguration;
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

    private final Context context;
    private final RecyclerConfiguration recyclerConfiguration;
    private UserListAdapterDataBinding adapter;

    public ListViewModelDataBinding(Context context) {
        this.context = context;

        isInProgress = false;
        isShowPlaceholder = true;
        isError = false;
        isShowList = false;

        title = "Data Binding";

        EventBus.getInstance().register(this);

        recyclerConfiguration = new RecyclerConfiguration();
        initRecyclerView();
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

                adapter.setPeopleList(peopleList);
                adapter.notifyDataSetChanged();
                recyclerConfiguration.notifyPropertyChanged(BR.adapter);
                recyclerConfiguration.notifyPropertyChanged(BR.person);

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

    private void initRecyclerView() {

        adapter = new UserListAdapterDataBinding();

        recyclerConfiguration.setLayoutManager(new LinearLayoutManager(context));
//        recyclerConfiguration.setItemAnimator(new DefaultItemAnimator());
        recyclerConfiguration.setAdapter(adapter);
    }

    @Bindable
    public RecyclerConfiguration getRecyclerConfiguration() {
        return recyclerConfiguration;
    }

    @Subscribe
    public void onNewPersonEvent(NewPersonEvent event) {
        loadList();
    }
}
