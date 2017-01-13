package com.isobar.sample.architecturepatterns.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fabio.goncalves on 12/01/2017.
 */

public class PersonDao {

    private int insertCount = 0;
    private Map<Integer, Person> people;
    private static PersonDao instance;

    public static PersonDao getInstance() {

        if  (instance == null ) {
            instance = new PersonDao();
        }
        return instance;
    }


    private PersonDao() {
        people = new HashMap();
        addAllPeople();
    }


    private void addAllPeople() {

        addPerson(new Person("John Doe", "john@doe", "jd", 10, "Martini"));
        addPerson(new Person("Mary Jane", "mary@jane", "mj", 11, "Beer"));

    }

    private Person addPerson(Person person) {

        Person p = new Person(person);

        if (p.pk == null) {
            p.pk = insertCount++;
        }
        people.put(p.pk, p);

        return p;
    }


    public Collection<Person> queryAll() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
}
