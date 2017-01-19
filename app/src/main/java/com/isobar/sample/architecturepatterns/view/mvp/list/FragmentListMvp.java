package com.isobar.sample.architecturepatterns.view.mvp.list;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.isobar.sample.architecturepatterns.R;
import com.isobar.sample.architecturepatterns.model.Person;
import com.isobar.sample.architecturepatterns.model.PersonDao;
import com.isobar.sample.architecturepatterns.view.common.CommonFragment;
import com.isobar.sample.architecturepatterns.view.mvc.FragmentListMvc;
import com.isobar.sample.architecturepatterns.view.mvp.interfaces.MvpFragment;
import com.isobar.sample.architecturepatterns.view.mvp.list.interfaces.ListMvpPresenter;
import com.isobar.sample.architecturepatterns.view.mvp.list.interfaces.ListMvpView;

import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fabio.goncalves on 13/01/2017.
 */
public class FragmentListMvp extends MvpFragment<ListMvpView, ListMvpPresenter> implements ListMvpView {

    private final static String TAG = FragmentListMvp.class.getSimpleName();

    @BindView(R.id.list_title)
    TextView titleView;

    @BindView(R.id.list_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.list_placeholder)
    LinearLayout placeholderLayout;

    @BindView(R.id.list_operation_in_progress)
    LinearLayout inProgressLayout;

    private ListMvpPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        ButterKnife.bind(this, view);

        titleView.setText("MVP");

        presenter = new ListMvpPresenterImp(this, PersonDao.getInstance());
        presenter.loadUserList();
        return view;
    }

    @Override
    public void onDetach() {
        Log.i(TAG,"Detach fragment");
        presenter.detachView();
        super.onDetach();
    }

    @OnClick(R.id.list_edit_user_button)
    void editUser() {
        presenter.editUser(getActivity().getSupportFragmentManager());
    }

    @OnClick(R.id.list_new_user_button)
    void newUser() {
        presenter.newUser(getActivity().getSupportFragmentManager());
    }

    @Override
    public void showList(Collection<Person> peopleList) {

        Log.i(TAG,"Showing list");
        recyclerView.setVisibility(View.VISIBLE);
        placeholderLayout.setVisibility(View.GONE);
        inProgressLayout.setVisibility(View.GONE);
    }

    @Override
    public void showSpinner() {
        recyclerView.setVisibility(View.GONE);
        placeholderLayout.setVisibility(View.GONE);
        inProgressLayout.setVisibility(View.VISIBLE);

    }

    @Override
    public void showPlaceholder() {
        recyclerView.setVisibility(View.GONE);
        placeholderLayout.setVisibility(View.VISIBLE);
        inProgressLayout.setVisibility(View.GONE);

    }

    public static void createAndOpen(FragmentManager supportFragmentManager) {

        clearStackAndAdd(supportFragmentManager, new FragmentListMvp());
    }

}
