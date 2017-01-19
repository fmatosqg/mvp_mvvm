package com.isobar.sample.architecturepatterns.view.mvp.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.isobar.sample.architecturepatterns.R;
import com.isobar.sample.architecturepatterns.model.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fabio.goncalves on 19/01/2017.
 */
class UserListAdapterMvp extends RecyclerView.Adapter<UserListAdapterMvp.ViewHolder> {

    private List<Person> personList;

    public UserListAdapterMvp() {
        personList = new LinkedList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_view, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (position < personList.size()) {
            Person person = personList.get(position);
            holder.email.setText(person.email);
            holder.userName.setText(person.name);
        } else {
            holder.email.setText("emaillll");
            holder.userName.setText("userrrrr");
        }

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public void setPersonList(Collection<Person> personList) {
        this.personList = new ArrayList<Person>(personList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.user_row_name)
        TextView userName;

        @BindView(R.id.user_row_email)
        TextView email;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
