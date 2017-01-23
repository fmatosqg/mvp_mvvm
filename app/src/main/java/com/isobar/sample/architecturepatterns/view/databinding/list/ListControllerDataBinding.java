package com.isobar.sample.architecturepatterns.view.databinding.list;

import android.util.Log;

/**
 * Created by fabio.goncalves on 23/01/2017.
 */

public class ListControllerDataBinding {

    private static final String TAG = ListControllerDataBinding.class.getSimpleName();

    public void onClickNewUser(ListViewModelDataBinding listViewModelDataBinding) {
        Log.i(TAG,"New user will be created");
    }
}
