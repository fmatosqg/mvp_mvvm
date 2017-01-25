package com.isobar.sample.architecturepatterns.view.databinding.list;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.isobar.sample.architecturepatterns.R;
import com.isobar.sample.architecturepatterns.view.common.CommonFragment;
import com.isobar.sample.architecturepatterns.view.databinding.list.viewmodel.ListControllerDataBinding;
import com.isobar.sample.architecturepatterns.view.databinding.list.viewmodel.ListViewModelDataBinding;

/**
 * Created by fabio.goncalves on 12/01/2017.
 */
public class FragmentListDataBinding extends CommonFragment {

    private final static String TAG = FragmentListDataBinding.class.getSimpleName();
    private ListBinder binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_db, container, false);

        binding.setListViewModel(new ListViewModelDataBinding(getActivity()));
        binding.setListController(new ListControllerDataBinding(getActivity().getSupportFragmentManager()));

        TextView title = binding.listTitleDb;
        title.setAllCaps(true);
        return binding.getRoot();

    }

    @Override
    public void onDestroy() {
        binding.getListViewModel().destroy();
        super.onDestroy();
    }

    public static void createAndOpen(FragmentManager supportFragmentManager) {

        clearStackAndAdd(supportFragmentManager, new FragmentListDataBinding());
    }

}
