package com.isobar.sample.architecturepatterns.view.databinding.util;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.isobar.sample.architecturepatterns.*;

/**
 * Created by fabio.goncalves on 24/01/2017.
 */
public class RecyclerConfiguration extends BaseObservable {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private RecyclerView.Adapter adapter;


    @Bindable
    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        notifyPropertyChanged(BR.layoutManager);
    }

//    @Bindable
//    public RecyclerView.ItemAnimator getItemAnimator() {
//        return itemAnimator;
//    }
//
//    public void setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
//        this.itemAnimator = itemAnimator;
//        notifyPropertyChanged(BR.itemAnimator);
//    }

    @Bindable
    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    @BindingAdapter("app:configuration") // this is an otherwise attr
    public static void configureRecyclerView(RecyclerView recyclerView, RecyclerConfiguration configuration) {
        recyclerView.setLayoutManager(configuration.getLayoutManager());
//        recyclerView.setItemAnimator(configuration.getItemAnimator());
        recyclerView.setAdapter(configuration.getAdapter());
    }
}