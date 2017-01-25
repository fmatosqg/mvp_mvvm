package com.isobar.sample.architecturepatterns.view.mvc;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.isobar.sample.architecturepatterns.R;
import com.isobar.sample.architecturepatterns.bus.EventBus;
import com.isobar.sample.architecturepatterns.bus.NewPersonEvent;
import com.isobar.sample.architecturepatterns.model.Person;
import com.isobar.sample.architecturepatterns.model.PersonDao;
import com.isobar.sample.architecturepatterns.model.RandomFailureException;
import com.isobar.sample.architecturepatterns.view.common.CommonFragment;
import com.squareup.otto.Subscribe;

import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by fabio.goncalves on 12/01/2017.
 */

public class FragmentListMvc extends CommonFragment {

    private final static String TAG = FragmentListMvc.class.getSimpleName();

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

    private AsyncTask<Void, Void, Collection<Person>> task;
    private UserListAdapterMvc adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        ButterKnife.bind(this, view);

        titleView.setText("MVC");

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        adapter = new UserListAdapterMvc();
        recyclerView.setAdapter(adapter);

        placeholderLayout.setVisibility(GONE);
        recyclerView.setVisibility(GONE);
        inProgressLayout.setVisibility(VISIBLE);
        Log.i(TAG, "Loading people started");

        loadPeopleList();

        EventBus.getInstance().register(this);

        return view;
    }

    private void loadPeopleList() {

        task = new AsyncTask<Void, Void, Collection<Person>>() {

            private boolean isDaoFailed = false;
            @Override
            protected Collection<Person> doInBackground(Void... voids) {
                Collection<Person> peopleList = null;
                try {
                    peopleList = PersonDao.getInstance().queryAll();
                } catch (RandomFailureException e) {
                    Log.i(TAG,e.getMessage());
                    isDaoFailed = true;
                }

                return peopleList;
            }

            @Override
            protected void onPostExecute(Collection<Person> peopleList) {

                inProgressLayout.setVisibility(GONE);
                if (peopleList != null && peopleList.size() > 0) {
                    recyclerView.setVisibility(VISIBLE);
                    placeholderLayout.setVisibility(GONE);
                } else {
                    recyclerView.setVisibility(GONE);
                    placeholderLayout.setVisibility(VISIBLE);
                }

                adapter.setPeopleList(peopleList);
                adapter.notifyDataSetChanged();

                errorView.setVisibility(isDaoFailed ? VISIBLE : GONE);

            }
        };

        task.execute();
    }

    @Override
    public void onDetach() {

        EventBus.getInstance().unregister(this);
        if (task != null) {
            task.cancel(true);
        }

        super.onDetach();
    }

    @OnClick(R.id.list_new_user_button)
    void newUser() {

        FragmentFormMvc.createAndOpen(getActivity().getSupportFragmentManager(), (Person) null);
    }

    public static void createAndOpen(FragmentManager supportFragmentManager) {

        clearStackAndAdd(supportFragmentManager, new FragmentListMvc());
    }

    @Subscribe
    public void onNewPersonEvent(NewPersonEvent event) {
        loadPeopleList();
    }
}
