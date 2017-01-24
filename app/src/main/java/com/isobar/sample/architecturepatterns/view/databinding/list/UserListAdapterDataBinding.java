package com.isobar.sample.architecturepatterns.view.databinding.list;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isobar.sample.architecturepatterns.R;
import com.isobar.sample.architecturepatterns.model.Person;
import com.isobar.sample.architecturepatterns.view.databinding.list.viewmodel.RowViewModelDataBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by fabio.goncalves on 24/01/2017.
 */

public class UserListAdapterDataBinding extends RecyclerView.Adapter<UserListAdapterDataBinding.ViewHolder> {

    private final static String TAG = UserListAdapterDataBinding.class.getSimpleName();

    private List<Person> listPeople;

    private List<ViewHolder> viewHolderList;

    public UserListAdapterDataBinding() {
        viewHolderList = new LinkedList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_row_view_db, parent, false);
        ViewHolder holder = new ViewHolder(v);
        viewHolderList.add(holder);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Person person = null;
        if (position < getItemCount()) {
            person = listPeople.get(position);
            if (position == 1) {
                person = null;
            }
        }

        holder.getBinding().setRowViewModel(new RowViewModelDataBinding(person));
    }

    @Override
    public int getItemCount() {

        int count = 0;
        if (listPeople != null) {
            count = listPeople.size();
        }

        return count;
    }

    public void setPeopleList(Collection<Person> peopleList) {

        if (peopleList != null) {
            this.listPeople = new ArrayList<>(peopleList);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final RowBinder binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public RowBinder getBinding() {
            return binding;
        }
    }
}
