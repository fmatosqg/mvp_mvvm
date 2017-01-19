package com.isobar.sample.architecturepatterns.model;

/**
 * Created by fabio.goncalves on 12/01/2017.
 */

public class Person {

    public String name, password, email, reminder;
    public int age;

    public Integer pk;

    public Person(String name, String email, String password, int age, String reminder) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
        this.pk = null;
        this.reminder = reminder;
    }


    public Person(Person p) {
        this.name = p.name;
        this.password = p.password;
        this.email = p.email;
        this.age = p.age;
        this.pk = p.pk;
        this.reminder = p.reminder;
    }

}
