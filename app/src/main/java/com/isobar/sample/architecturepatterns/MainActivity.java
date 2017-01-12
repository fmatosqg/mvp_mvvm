package com.isobar.sample.architecturepatterns;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.isobar.sample.architecturepatterns.mvc.FragmentFormMvc;
import com.isobar.sample.architecturepatterns.mvc.FragmentListMvc;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        Fragment fragment = new FragmentListMvc();
        fragment = new FragmentFormMvc();

        if (savedInstanceState != null) {

            Fragment f =  getSupportFragmentManager().findFragmentByTag(getClass().getCanonicalName());
            if (f != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .remove(f)
                        .commit();
            }
        }

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment, getClass().getCanonicalName())
                .commit();

        getFragmentManager().executePendingTransactions();*/
    }
}
