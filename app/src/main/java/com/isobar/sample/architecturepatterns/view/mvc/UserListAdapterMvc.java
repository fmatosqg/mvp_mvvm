package com.isobar.sample.architecturepatterns.view.mvc;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
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
import butterknife.OnClick;

/**
 * Created by fabio.goncalves on 18/01/2017.
 */
public class UserListAdapterMvc extends RecyclerView.Adapter<UserListAdapterMvc.ViewHolder> {

    private List<Person> personList;

    public UserListAdapterMvc() {
        personList = new LinkedList<>();
    }

    @Override
    public UserListAdapterMvc.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_view, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(UserListAdapterMvc.ViewHolder holder, int position) {

        Person person = null;

        if (position < personList.size()) {
            person = personList.get(position);
            holder.userName.setText(person.name);
            holder.email.setText(person.email);
            holder.person = person;
        } else {
            holder.userName.setText("");
            holder.email.setText("");
            holder.person = null;
        }
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public void setPeopleList(Collection<Person> cachedPeopleList) {
        if (cachedPeopleList != null) {
            this.personList = new ArrayList<>(cachedPeopleList);
        } else {
            this.personList = new LinkedList<>();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.user_row_name)
        TextView userName;

        @BindView(R.id.user_row_email)
        TextView email;

        Person person;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.user_row_layout)
        public void onClick(View view) {
            FragmentFormMvc.createAndOpen(getActivityContext().getSupportFragmentManager(), person);
        }

        private AppCompatActivity getActivityContext() {
            Context context = userName.getContext();

            if (context instanceof AppCompatActivity) {
                return (AppCompatActivity) context;
            } else {
                throw new RuntimeException("AAAAAhhhh contexts are complex! " + context.getClass().getCanonicalName());
            }

        }
    }


}
