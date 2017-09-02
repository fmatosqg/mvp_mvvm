package com.isobar.sample.architecturepatterns.view.rxMvp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.isobar.sample.architecturepatterns.R;
import com.isobar.sample.architecturepatterns.view.common.CommonFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by fmatos on 2/09/2017.
 */

public class FragmentListRxMvp extends CommonFragment {


    @BindView(R.id.list_title)
    TextView titleView;

    @BindView(R.id.list_error)
    TextView errorView;


    @BindView(R.id.list_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.list_placeholder)
    LinearLayout placeholderLayout;

    @BindView(R.id.list_operation_in_progress)
    LinearLayout inProgressLayout;


    private ListRxMvpPresenter presenter;
    private CompositeDisposable compositeDisposable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        ButterKnife.bind(this, view);


        compositeDisposable = new CompositeDisposable();
        presenter = new ListRxMvpPresenter();

        compositeDisposable.add(
                presenter.subscribe(new DisposableObserver<ListStateRxMvp>() {
                                                 @Override
                                                 public void onNext(ListStateRxMvp newState) {
                                                     updateView(newState);
                                                 }

                                                 @Override
                                                 public void onError(Throwable e) {

                                                 }

                                                 @Override
                                                 public void onComplete() {

                                                 }
                                             }
                ));

        presenter.loadUserList();

        return view;
    }

    @OnClick(R.id.list_new_user_button)
    void newUser() {
        presenter.newUser(getActivity().getSupportFragmentManager());
    }


    private void updateView(ListStateRxMvp newState) {

        errorView.setVisibility(newState.showError ? View.VISIBLE : View.GONE);
        placeholderLayout.setVisibility(newState.showPlaceholder ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(newState.showList ? View.VISIBLE : View.GONE);
        inProgressLayout.setVisibility(newState.showSpinner ? View.VISIBLE : View.GONE);

    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        compositeDisposable = null;
        super.onDestroy();
    }

    public static void createAndOpen(FragmentManager supportFragmentManager) {

        clearStackAndAdd(supportFragmentManager, new FragmentListRxMvp());
    }
}
