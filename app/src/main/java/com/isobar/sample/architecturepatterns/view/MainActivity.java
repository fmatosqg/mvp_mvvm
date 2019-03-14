package com.isobar.sample.architecturepatterns.view;


import android.os.Bundle;
import android.support.design.widget.TabLayout;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.isobar.sample.architecturepatterns.R;
import com.isobar.sample.architecturepatterns.view.databinding.list.FragmentListDataBinding;
import com.isobar.sample.architecturepatterns.view.mvc.FragmentListMvc;
import com.isobar.sample.architecturepatterns.view.mvp.list.FragmentListMvp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();
    @BindView(R.id.activity_tabs)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        updateFragment(0);

    }

    private void updateFragment(int position) {

        TabLayout.Tab tab = tabLayout.getTabAt(position);

        Log.i(TAG, "Tab name is " + tab.getText());


        if ( "MVC".equals(tab.getText()) ) {
            FragmentListMvc.createAndOpen(getSupportFragmentManager());
        } else if ( "MVP".equals(tab.getText()) ) {
            FragmentListMvp.createAndOpen(getSupportFragmentManager());
        } else if ( "Data Binding".equals(tab.getText()) ) {
            FragmentListDataBinding.createAndOpen(getSupportFragmentManager());
        }


    }

}
