package com.isobar.sample.architecturepatterns.view.mvc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isobar.sample.architecturepatterns.R;
import com.isobar.sample.architecturepatterns.model.Person;
import com.isobar.sample.architecturepatterns.model.PersonDao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;

/**
 * Created by fabio.goncalves on 12/01/2017.
 */

public class FragmentListMvc extends Fragment {

    private final static String TAG = FragmentListMvc.class.getCanonicalName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.list_edit_user_button)
    void editUser() {
        Log.i(TAG, "Edit");

        Person person = PersonDao.getInstance().queryAll().iterator().next();

        FragmentFormMvc.createAndOpen(getActivity().getSupportFragmentManager(),person);
    }

    @OnClick(R.id.list_new_user_button)
    void newUser() {

        FragmentFormMvc.createAndOpen(getActivity().getSupportFragmentManager(),null);
    }

    public static void createAndOpen(FragmentManager supportFragmentManager) {
        Fragment fragment = new FragmentListMvc();
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.getClass().getCanonicalName())
                .commit();

        supportFragmentManager.executePendingTransactions();
    }
}
