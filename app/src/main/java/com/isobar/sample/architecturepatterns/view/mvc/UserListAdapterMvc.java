package com.isobar.sample.architecturepatterns.view.mvc;

import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.isobar.sample.architecturepatterns.R;
import com.isobar.sample.architecturepatterns.model.Person;
import com.isobar.sample.architecturepatterns.model.PersonDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fabio.goncalves on 18/01/2017.
 */

public class UserListAdapterMvc extends RecyclerView.Adapter<UserListAdapterMvc.ViewHolder> {

    private PersonDao personDao;
    private List<Person> cachedPeopleList;

    @Override
    public UserListAdapterMvc.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_view, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;

    }

    @Override
    public void onBindViewHolder(UserListAdapterMvc.ViewHolder holder, int position) {

        Person person = null;

        if (cachedPeopleList != null && position < cachedPeopleList.size()) {
            person = cachedPeopleList.get(position);
        }

        if (person != null) {
            holder.userName.setText(person.name);
            holder.email.setText(person.email);
        } else {
            holder.userName.setText("");
            holder.email.setText("");
        }
    }


    @Override
    public int getItemCount() {
        if (cachedPeopleList != null) {
            return cachedPeopleList.size();
        } else {
            return 0;
        }
    }

    public void getPeople(final DatabaseLoadListener listener) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (cachedPeopleList == null || isCacheTooOld()) {
                    cachedPeopleList = new ArrayList<Person>(getPersonDao().queryAll());
                }

                if (listener != null) {
                    listener.onDataReceived(cachedPeopleList);
                }
            }
        });

        thread.start();

    }

    private boolean isCacheTooOld() {
        return false;
    }

    private PersonDao getPersonDao() {
        if (personDao == null) {
            personDao = PersonDao.getInstance();
        }
        return personDao;
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

    public interface DatabaseLoadListener {

        @UiThread
        void onDataReceived(Collection<Person> cachedPeopleList);
    }
}
