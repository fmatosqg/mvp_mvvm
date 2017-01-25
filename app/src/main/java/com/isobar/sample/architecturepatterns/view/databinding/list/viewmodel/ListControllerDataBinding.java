package com.isobar.sample.architecturepatterns.view.databinding.list.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

import com.isobar.sample.architecturepatterns.BR;
import com.isobar.sample.architecturepatterns.view.databinding.form.FragmentFormDataBinding;

/**
 * Created by fabio.goncalves on 23/01/2017.
 */

public class ListControllerDataBinding extends BaseObservable {


    private static final String TAG = ListControllerDataBinding.class.getSimpleName();
    final private FragmentManager supportFragmentManager;
    private int visibility;

    public ListControllerDataBinding(FragmentManager supportFragmentManager) {
        this.supportFragmentManager = supportFragmentManager;
        visibility = View.VISIBLE;

        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean flag = false;
                try {
                    for (int i = 0; i < 30; i++) {

                        setShowList(flag);
                        flag = !flag;
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {


                }

            }
        }).start();
    }


    public void onClickNewUser(ListViewModelDataBinding listViewModelDataBinding) {
        Log.i(TAG, "New user will be created");
        FragmentFormDataBinding.createAndOpen(supportFragmentManager, null);
    }

    @Bindable
    public int getShowList() {
        return visibility;
    }

    private void setShowList(boolean isVisible) {
        visibility = isVisible ? View.VISIBLE : View.GONE;
        notifyPropertyChanged(BR.showList);
    }
}
