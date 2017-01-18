package com.isobar.sample.architecturepatterns.view.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.isobar.sample.architecturepatterns.R;

/**
 * Created by fabio.goncalves on 18/01/2017.
 */

public class CommonFragment extends Fragment {

    public static void createAndOpen(FragmentManager supportFragmentManager, Fragment fragment) {

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.getClass().getCanonicalName())
                .commit();

        supportFragmentManager.executePendingTransactions();
    }
}
