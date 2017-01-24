package com.isobar.sample.architecturepatterns.view.databinding.list;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isobar.sample.architecturepatterns.R;
import com.isobar.sample.architecturepatterns.view.common.CommonFragment;

/**
 * Created by fabio.goncalves on 12/01/2017.
 */
public class FragmentListDataBinding extends CommonFragment {

    private final static String TAG = FragmentListDataBinding.class.getSimpleName();
    private ListViewModelDataBinding modelDataBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ListBinder binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_db, container, false);

        modelDataBinding = new ListViewModelDataBinding(getActivity());
        binding.setListViewModel(modelDataBinding);
        binding.setListController(new ListControllerDataBinding(getActivity().getSupportFragmentManager()));
        return binding.getRoot();

    }

    @Override
    public void onDestroy() {
        modelDataBinding.destroy();
        super.onDestroy();
    }

    public static void createAndOpen(FragmentManager supportFragmentManager) {

        clearStackAndAdd(supportFragmentManager, new FragmentListDataBinding());
    }

}
