package com.isobar.sample.architecturepatterns.view.databinding.list.viewmodel;

import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.isobar.sample.architecturepatterns.view.databinding.form.FragmentFormDataBinding;

/**
 * Created by fabio.goncalves on 23/01/2017.
 */

public class ListControllerDataBinding {

    private static final String TAG = ListControllerDataBinding.class.getSimpleName();
    final private FragmentManager supportFragmentManager;

    public ListControllerDataBinding(FragmentManager supportFragmentManager) {
        this.supportFragmentManager = supportFragmentManager;
    }


    public void onClickNewUser(ListViewModelDataBinding listViewModelDataBinding) {
        Log.i(TAG, "New user will be created");
        FragmentFormDataBinding.createAndOpen(supportFragmentManager, null);
    }
}
