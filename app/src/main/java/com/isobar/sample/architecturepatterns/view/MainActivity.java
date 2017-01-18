package com.isobar.sample.architecturepatterns.view;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.isobar.sample.architecturepatterns.R;
import com.isobar.sample.architecturepatterns.model.PersonDao;
import com.isobar.sample.architecturepatterns.view.mvc.FragmentListMvc;
import com.isobar.sample.architecturepatterns.view.mvp.list.FragmentListMvp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();
    @BindView(R.id.activity_tabs)
    TabLayout tabLayout;

    private PersonDao personDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        personDao = PersonDao.getInstance();


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

        switch (position) {
            case 0: // MVC
                FragmentListMvc.createAndOpen(getSupportFragmentManager());
                break;
            case 1: // MVP
                FragmentListMvp.createAndOpen(getSupportFragmentManager());
                break;
            default:

        }
    }

}
