package com.isobar.sample.architecturepatterns.view.databinding.list;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isobar.sample.architecturepatterns.R;
import com.isobar.sample.architecturepatterns.bus.EventBus;
import com.isobar.sample.architecturepatterns.bus.NewPersonEvent;



import com.isobar.sample.architecturepatterns.view.common.CommonFragment;
import com.squareup.otto.Subscribe;

/**
 * Created by fabio.goncalves on 12/01/2017.
 */
public class FragmentListDataBinding extends CommonFragment {

    private final static String TAG = FragmentListDataBinding.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_db, container, false);

        binding.setListViewModel(new ListViewModelDataBinding());
        binding.setListController(new ListControllerDataBinding());
        return binding.getRoot();

    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getInstance().register(this);
    }

    @Override
    public void onPause() {
        EventBus.getInstance().unregister(this);
        super.onPause();
    }

    public static void createAndOpen(FragmentManager supportFragmentManager) {

        clearStackAndAdd(supportFragmentManager, new FragmentListDataBinding());
    }

    @Subscribe
    public void onNewPersonEvent(NewPersonEvent event) {
//        loadPeopleList();
    }
}
