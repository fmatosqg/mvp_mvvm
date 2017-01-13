package com.isobar.sample.architecturepatterns.view;


import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.isobar.sample.architecturepatterns.R;
import com.isobar.sample.architecturepatterns.model.Person;
import com.isobar.sample.architecturepatterns.model.PersonDao;
import com.isobar.sample.architecturepatterns.view.mvc.FragmentFormMvc;
import com.isobar.sample.architecturepatterns.view.mvc.FragmentListMvc;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();
    @BindView(R.id.activity_tabs)
    TabLayout tabLayout;

    private PersonDao personDao;


//    @BindView(R.id.activity_tab_mvc) TabItem tabMvc;
//    @BindView(R.id.activity_tab_data_binding) TabItem tabDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        personDao = PersonDao.getInstance();


        TabItem db = (TabItem) findViewById(R.id.activity_tab_data_binding);
        if ( db != null ) {
            db.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "aaaaa");
                }
            });
        }


        FragmentListMvc.createAndOpen(getSupportFragmentManager());

    }


    private void startMvc2(Bundle savedInstanceState, Person person) {

        person = personDao.queryAll().iterator().next();

        Fragment fragment = new FragmentFormMvc();
        Bundle bundle = new Bundle();


        bundle.putInt(FragmentFormMvc.BUNDLE_ARGUMENT_PERSON_PK,person.pk);
        fragment.setArguments(bundle);

        if (savedInstanceState != null) {

            Fragment f = getSupportFragmentManager().findFragmentByTag(getClass().getCanonicalName());
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

        getFragmentManager().executePendingTransactions();
    }

    @OnClick(R.id.activity_tabs)
    void tabDataBinding(View view) {
        Log.i(TAG, "data binding");
    }

}
