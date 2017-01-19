package com.isobar.sample.architecturepatterns.view.mvp.list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.isobar.sample.architecturepatterns.model.Person;
import com.isobar.sample.architecturepatterns.model.PersonDao;
import com.isobar.sample.architecturepatterns.view.mvp.list.interfaces.ListMvpPresenter;
import com.isobar.sample.architecturepatterns.view.mvp.list.interfaces.ListMvpView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

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


    @Before
    public void setup() {

        Logger.getAnonymousLogger().log(Level.INFO, "Initiate classes");
        view = Mockito.mock(ListMvpView.class);
        personDaoOnePerson = Mockito.mock(PersonDao.class);

        Collection<Person> onePerson = new LinkedList<>();
        onePerson.add(Mockito.mock(Person.class));

        Mockito.when(personDaoOnePerson.queryAll()).thenReturn(onePerson);

        listMvpPresenter = new ListMvpPresenterImp(view, personDaoOnePerson);

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
        Mockito.verify(view, times(1)).showList(Matchers.anyCollection());
        Mockito.verify(view, times(1)).showPlaceholder();
    }


    @Test
    public void whenAddNewUserThenChangeFragment() {

        Mockito.verify(fragmentManager,times(0)).beginTransaction();
        listMvpPresenter.newUser(fragmentManager);

        Mockito.verify(fragmentManager,times(1)).beginTransaction();
        Mockito.verify(fragmentTransaction,times(1)).replace(Matchers.anyInt(), (Fragment) Matchers.anyObject(),Matchers.anyString());
    }
}