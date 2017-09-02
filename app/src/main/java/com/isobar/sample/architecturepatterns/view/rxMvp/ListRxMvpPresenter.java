package com.isobar.sample.architecturepatterns.view.rxMvp;

import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.isobar.sample.architecturepatterns.bus.NewPersonEvent;
import com.isobar.sample.architecturepatterns.model.Person;
import com.isobar.sample.architecturepatterns.model.PersonDao;
import com.isobar.sample.architecturepatterns.view.mvc.FragmentFormMvc;

import java.util.Collection;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ComputationScheduler;
import io.reactivex.internal.schedulers.IoScheduler;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;


/**
 * Created by fmatos on 2/09/2017.
 */

public class ListRxMvpPresenter {

    private static final String TAG = ListRxMvpPresenter.class.getSimpleName();

    final private Subject<ListStateRxMvp> stateObservable;

    private ListStateRxMvp state;

    final private PersonDao personDao;

    final private CompositeDisposable compositeDisposable;


    ListRxMvpPresenter(final PersonDao personDao) {
        this.personDao = personDao;
        stateObservable = BehaviorSubject.create();

        compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(personDao
                .getNewPersonObservable()
                .subscribeWith(new DisposableObserver<NewPersonEvent>() {
                    @Override
                    public void onNext(NewPersonEvent newPersonEvent) {
                        Log.i(TAG, "Database notified me of new person");
                        loadPeopleList();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                })
                );
        state = new ListStateRxMvp();
        stateObservable.onNext(state);
    }

    public void newUser(FragmentManager fragmentManager) {
        FragmentFormMvc.createAndOpen(fragmentManager, (Person) null);
    }

    public void loadUserList() {

        state = new ListStateRxMvp(false, true, false, false);
        stateObservable.onNext(state);

        loadPeopleList();
    }


    private void loadPeopleList() {

        Disposable disposable = null;

        Observable<Collection<Person>> observable;

        Callable callable = new Callable<Collection<Person>>() {
            public Collection<Person> call() {
                Log.i(TAG, "Database being queried");
                Collection<Person> all = personDao.queryAll();
                Log.i(TAG, "Database finished");

                return all;
            }
        };


        observable = Observable
                .fromCallable(callable)
                .observeOn(new ComputationScheduler())
                .subscribeOn(new IoScheduler());

        disposable = observable
                .subscribeWith(new DisposableObserver<Collection<Person>>() {

                    @Override
                    public void onNext(Collection<Person> personCollection) {

                        Log.i(TAG, "Database values about to send to new state");
                        state = new ListStateRxMvp(true, false, false, false);
                        state.setPersonList(personCollection);

                        stateObservable.onNext(state);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "Database values about to send to new state");

                        state = new ListStateRxMvp(false, false, false, true);

                        stateObservable.onNext(state);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        compositeDisposable.add(disposable);
    }

    public void stop(){
        compositeDisposable.clear();
    }

    public Observable<ListStateRxMvp> getStateObservable() {
        return stateObservable
                .share();
    }
}
