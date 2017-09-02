package com.isobar.sample.architecturepatterns.model;

import android.util.Log;

import com.isobar.sample.architecturepatterns.bus.EventBus;
import com.isobar.sample.architecturepatterns.bus.NewPersonEvent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by fabio.goncalves on 12/01/2017.
 */

public class PersonDao {

    private static final String TAG = PersonDao.class.getSimpleName();

    private int insertCount = 0;
    private Map<Integer, Person> people;
    private static PersonDao instance;
    private boolean failureProgrammed = true;

    final private Subject<NewPersonEvent> newPersonEventSubject;

    public static PersonDao getInstance() {

        if (instance == null) {
            instance = new PersonDao();
        }
        return instance;
    }


    private PersonDao() {
        people = new HashMap();
        newPersonEventSubject = BehaviorSubject.create();
        addAllPeople();
    }

    private void addAllPeople() {

        addPerson(new Person("John Doe", "john@doe", "jd", 10, "Martini"));
        addPerson(new Person("Mary Jane", "mary@jane", "mj", 11, "Beer"));

        failureProgrammed = false;
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    failureProgrammed = true;
                    addPerson(new Person("Baby Bob", "bob@justBorn.com", "bb", 0, "Milk"));
                    EventBus.getInstance().postOnAnyThread(new NewPersonEvent());
                    newPersonEventSubject.onNext(new NewPersonEvent());
                    Log.i(TAG, "New baby is born");

                    Thread.sleep(4000);
                    failureProgrammed = false;
                    addPerson(new Person("Baby Be", "b@justBorn.com", "bb", 0, "Milk"));
                    EventBus.getInstance().postOnAnyThread(new NewPersonEvent());
                    newPersonEventSubject.onNext(new NewPersonEvent());
                    Log.i(TAG, "Another baby is born");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        th.start();



    }

    synchronized private Person addPerson(Person person) {

        Person p = new Person(person);

        if (p.pk == null) {
            p.pk = insertCount++;
        }
        people.put(p.pk, p);

        return p;
    }

    synchronized public int queryCount() {
        return people.values().size();
    }

    synchronized public Collection<Person> queryAll() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Log.i(TAG, "Sleep interrupted!!!");
        }

        if ( failureProgrammed ) {
            throw new RandomFailureException("I don't like being queried on Mondays");
        }
        return people.values();
    }

    public Person queryId(Integer pk) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return people.get(pk);
    }

    public Person updatePerson(Person p) {

        return addPerson(p);

    }

    public Observable<NewPersonEvent> getNewPersonObservable() {

        return newPersonEventSubject.share();
    }

}
