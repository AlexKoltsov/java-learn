package com.example.java_learn.bookstore.person;

import java.util.Date;

public class Person {

    private static int countPersons;

    protected String firstName;
    protected String secondName;
    protected String country;
    protected Date birthday;

    public Person() {
        this("null", "null", "null", new Date(0));
    }

    public Person(String firstName, String secondName, String country, Date birthday) {
        countPersons++;
        this.firstName = firstName;
        this.secondName = secondName;
        this.country = country;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public static int getCountPersons() {
        return countPersons;
    }
}
