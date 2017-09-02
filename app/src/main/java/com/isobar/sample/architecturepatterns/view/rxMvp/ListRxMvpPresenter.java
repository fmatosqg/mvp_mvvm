package com.isobar.sample.architecturepatterns.view.rxMvp;

import android.support.v4.app.FragmentManager;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by fmatos on 2/09/2017.
 */

public class ListRxMvpPresenter {

    final private Subject<ListStateRxMvp> observable;

    private ListStateRxMvp state;

    ListRxMvpPresenter() {
        observable = BehaviorSubject.create();

        state = new ListStateRxMvp();
        observable.onNext(state);
    }

    // view calls these
    public void newUser(FragmentManager activity) {

    }

    public void loadUserList() {

        state = new ListStateRxMvp(false, true, false, false);

        observable.onNext(state);
    }

    public Disposable subscribe(DisposableObserver<ListStateRxMvp> observer) {

        Disposable disposable;

        disposable = observable
                .share()
                .subscribeWith(observer);

        return disposable;

    }
}
