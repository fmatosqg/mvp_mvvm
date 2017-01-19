package com.isobar.sample.architecturepatterns.view.mvp.list;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.isobar.sample.architecturepatterns.bus.EventBus;
import com.isobar.sample.architecturepatterns.bus.NewPersonEvent;
import com.isobar.sample.architecturepatterns.model.Person;
import com.isobar.sample.architecturepatterns.model.PersonDao;
import com.isobar.sample.architecturepatterns.view.mvp.list.interfaces.ListMvpPresenter;
import com.isobar.sample.architecturepatterns.view.mvp.list.interfaces.ListMvpView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import java.util.Collection;
import java.util.LinkedList;

import static org.mockito.Mockito.times;

/**
 * Created by fabio.goncalves on 19/01/2017.
 */
@RunWith(RobolectricTestRunner.class)
public class ListMvpPresenterImpTest {

    private ListMvpPresenter listMvpPresenter;
    private ListMvpView view;
    private PersonDao personDaoOnePerson;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private UserListAdapterMvp adapter;


    @Before
    public void setup() {

        view = Mockito.mock(ListMvpView.class);
        personDaoOnePerson = Mockito.mock(PersonDao.class);

        Collection<Person> onePerson = new LinkedList<>();
        onePerson.add(Mockito.mock(Person.class));

        Mockito.when(personDaoOnePerson.queryAll()).thenReturn(onePerson);

        adapter = Mockito.mock(UserListAdapterMvp.class);
        listMvpPresenter = new ListMvpPresenterImp(view, personDaoOnePerson, adapter);

        fragmentManager = Mockito.mock(FragmentManager.class);
        fragmentTransaction = Mockito.mock(FragmentTransaction.class);
        Mockito.when(fragmentManager.beginTransaction()).thenReturn(fragmentTransaction);
    }

    @Test
    public void whenAttachViewShowPlaceholder() {
        Mockito.verify(view, times(1)).showPlaceholder();
    }

    @Test
    public void whenLoadDataShowSpinner() {

        Mockito.verify(personDaoOnePerson, times(0)).queryAll();
        Mockito.verify(view, times(0)).showSpinner();


        listMvpPresenter.loadUserList();

        Mockito.verify(personDaoOnePerson, times(1)).queryAll();

        Mockito.verify(view, times(1)).showSpinner();
        Mockito.verify(view, times(1)).showList();
        Mockito.verify(view, times(1)).showPlaceholder();
    }

    @Test
    public void whenLoaDataNotifyAdapter() {

        listMvpPresenter.loadUserList();

        Mockito.verify(adapter,times(1)).notifyDataSetChanged();
    }

    @Test
    public void whenNewUserNotifyAdapter() {

        EventBus.getInstance().postOnAnyThread(new NewPersonEvent());

        Mockito.verify(adapter,times(1)).notifyDataSetChanged();

    }

    @Test
    public void whenLoadAndNewUserThenNotifyAdapterTwice() {


        listMvpPresenter.loadUserList();

        Mockito.verify(adapter,times(1)).notifyDataSetChanged();

        EventBus.getInstance().postOnAnyThread(new NewPersonEvent());

        Mockito.verify(adapter,times(2)).notifyDataSetChanged();

    }

}