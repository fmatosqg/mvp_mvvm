package com.isobar.sample.architecturepatterns.view.mvp.list;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.isobar.sample.architecturepatterns.R;
import com.isobar.sample.architecturepatterns.view.common.CommonFragment;
import com.isobar.sample.architecturepatterns.view.mvc.FragmentListMvc;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fabio.goncalves on 13/01/2017.
 */
public class FragmentListMvp extends CommonFragment implements ListMvpView {

    private final static String TAG = FragmentListMvc.class.getCanonicalName();

    @BindView(R.id.list_title)
    TextView titleView;

    private ListMvpPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        ButterKnife.bind(this, view);

        presenter = new ListMvpPresenterImp();
        presenter.attachView(this);

        titleView.setText("MVP");
        return view;
    }

    @OnClick(R.id.list_edit_user_button)
    void editUser() {
        presenter.editUser();
    }

    @OnClick(R.id.list_new_user_button)
    void newUser(){
        presenter.newUser();
    }


    public static void createAndOpen(FragmentManager supportFragmentManager) {

        createAndOpen(supportFragmentManager,new FragmentListMvp());
    }


}
