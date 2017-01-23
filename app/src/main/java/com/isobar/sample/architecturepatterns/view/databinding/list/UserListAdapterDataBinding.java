package com.isobar.sample.architecturepatterns.view.databinding.list;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isobar.sample.architecturepatterns.R;
import com.isobar.sample.architecturepatterns.model.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by fabio.goncalves on 18/01/2017.
 */
public class UserListAdapterDataBinding extends RecyclerView.Adapter<UserListAdapterDataBinding.ViewHolder> {

    private List<Person> personList;

    public UserListAdapterDataBinding() {
        personList = new LinkedList<>();
    }

    @Override
    public UserListAdapterDataBinding.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_view, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(UserListAdapterDataBinding.ViewHolder holder, int position) {

//        Person person = null;
//
//        if (position < personList.size()) {
//            person = personList.get(position);
//            holder.userName.setText(person.name);
//            holder.email.setText(person.email);
//            holder.person = person;
//        } else {
//            holder.userName.setText("");
//            holder.email.setText("");
//            holder.person = null;
//        }
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

        Person person;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void onClick(View view) {
//            FragmentFormMvc.createAndOpen(getActivityContext().getSupportFragmentManager(), person);
        }

    }

}



