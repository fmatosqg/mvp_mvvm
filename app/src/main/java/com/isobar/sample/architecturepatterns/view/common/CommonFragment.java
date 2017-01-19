package com.isobar.sample.architecturepatterns.view.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.isobar.sample.architecturepatterns.R;

import java.util.List;

/**
 * Created by fabio.goncalves on 18/01/2017.
 */

public class CommonFragment extends Fragment {

    final private static String TAG = CommonFragment.class.getSimpleName();

    public static void clearStackAndAdd(FragmentManager supportFragmentManager, Fragment fragment) {

        int depth = 0;

        List<Fragment> list = supportFragmentManager.getFragments();
        if (list != null) {
            depth = list.size();
        }

        FragmentTransaction transaction = supportFragmentManager
                .beginTransaction();

//        Log.i(TAG, "Depth is " + depth);
        for (int i = 0; i < depth; ++i) {
            Fragment f = list.get(i);
            if (f != null) {
//                Log.i(TAG, "Remove framgent" + f.getClass().getName() + " tag " + f.getTag());
                //transaction.remove(f);
            } else {
//                Log.i(TAG, "F IS NULL!!!!!!");
            }
        }

        transaction.replace(R.id.container, fragment, fragment.getClass().getSimpleName())
                .commit();

        supportFragmentManager.executePendingTransactions();

    }

    public static void addToStack(FragmentManager supportFragmentManager, Fragment fragment) {

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment, fragment.getClass().getCanonicalName())
                .commit();

        supportFragmentManager.executePendingTransactions();
    }
}
