package com.isobar.sample.architecturepatterns.view.mvc;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.TimingLogger;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.isobar.sample.architecturepatterns.R;
import com.isobar.sample.architecturepatterns.model.Person;
import com.isobar.sample.architecturepatterns.model.PersonDao;
import com.isobar.sample.architecturepatterns.view.common.CommonFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fabio.goncalves on 12/01/2017.
 */

public class FragmentListMvc extends CommonFragment {

    private final static String TAG = FragmentListMvc.class.getCanonicalName();

    @BindView(R.id.list_title)
    TextView titleView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        ButterKnife.bind(this, view);

        titleView.setText("MVC");

        return view;
    }

    @OnClick(R.id.list_edit_user_button)
    void editUser() {

        TimingLogger timings = new TimingLogger("TAG_MYJOB", "MyJob");
        timings.addSplit("Start");
        Log.i(TAG, "Edit");

        Person person = PersonDao.getInstance().queryAll().iterator().next();
        timings.addSplit("Query");
        FragmentFormMvc.createAndOpen(getActivity().getSupportFragmentManager(), person);
        timings.addSplit("Fragment transition");
        timings.dumpToLog();
    }

    @OnClick(R.id.list_new_user_button)
    void newUser() {

        FragmentFormMvc.createAndOpen(getActivity().getSupportFragmentManager(), (Person)null);
    }

    public static void createAndOpen(FragmentManager supportFragmentManager) {

        createAndOpen(supportFragmentManager, new FragmentListMvc());
    }
}
