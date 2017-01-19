package com.isobar.sample.architecturepatterns.view.mvp.list;

import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.TimingLogger;

import com.isobar.sample.architecturepatterns.model.Person;
import com.isobar.sample.architecturepatterns.model.PersonDao;
import com.isobar.sample.architecturepatterns.view.mvc.FragmentFormMvc;
import com.isobar.sample.architecturepatterns.view.mvp.list.interfaces.ListMvpPresenter;
import com.isobar.sample.architecturepatterns.view.mvp.list.interfaces.ListMvpView;

import java.lang.ref.WeakReference;
import java.util.Collection;

/**
 * Created by fabio.goncalves on 13/01/2017.
 */
public class ListMvpPresenterImp implements ListMvpPresenter {

    private static final String TAG = ListMvpPresenterImp.class.getSimpleName();
    private WeakReference<ListMvpView> weakView;
    private boolean isAttached;

    private PersonDao personDao;
    private AsyncTask<Void, Void, Collection<Person>> task;

    private ListMvpPresenterImp() {
    }

    public ListMvpPresenterImp(ListMvpView view) {
        isAttached = false;
        personDao = PersonDao.getInstance();
        task = null;
        attachView(view);
    }

    @Override
    public void editUser(FragmentManager fragmentManager) {

        TimingLogger timings = new TimingLogger("TAG_MYJOB", "MyJob");
        timings.addSplit("Start");
        Log.i(TAG, "Edit");

        Person person = PersonDao.getInstance().queryAll().iterator().next();
        timings.addSplit("Query");
        FragmentFormMvc.createAndOpen(fragmentManager, person);
        timings.addSplit("Fragment transition");
        timings.dumpToLog();
    }

    @Override
    public void newUser(FragmentManager fragmentManager) {

        FragmentFormMvc.createAndOpen(fragmentManager, (Person) null);
    }

    @Override
    public void loadUserList() {

        if (!isViewAttached()) {
            return;
        }
        weakView.get().showSpinner();
        Log.i(TAG, "MVP - Loading people started");

        task = new AsyncTask<Void, Void, Collection<Person>>() {
            @Override
            protected Collection<Person> doInBackground(Void... voids) {
                Collection<Person> people = personDao.queryAll();
                Log.i(TAG, "MVP - Loading people done");
                return people;
            }

            @Override
            protected void onPostExecute(Collection<Person> personList) {

                if (!isViewAttached()) {
                    Log.i(TAG, "Can't touch the view anymore");
                    return;
                }
                Log.i(TAG, "MVP - Loading people on screen");

                if (personList.size() > 0) {
                    weakView.get().showList(personList);
                } else {
                    weakView.get().showPlaceholder();
                }
            }
        };

        task.execute();
    }

    @Override
    public void attachView(ListMvpView view) {
        weakView = new WeakReference<ListMvpView>(view);
        isAttached = true;
    }

    @Override
    public boolean isViewAttached() {
        return isAttached && weakView.get() != null;
    }

    @Override
    public void detachView() {
        task.cancel(true);
        weakView = null;
        isAttached = false;
    }
}
