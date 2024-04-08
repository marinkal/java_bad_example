package com.company;

public class Person {
    private String fio;
    private int age;
    private boolean hasDriverLicense;
    private boolean isMarried;
    private boolean isRussianCitizen;

    private PersonState personState;

    public Person(String fio, int age) {
        this.fio = fio;
        this.age = age;
        this.isRussianCitizen = true;
        this.personState = PersonState.NOTHING;
    }

    public void setDriverLicense(boolean value) {
        this.hasDriverLicense = value;
    }

    public void setMarriedStatus(boolean value) {
        this.isMarried = value;
    }

    public void setPersonState(PersonState personState) {
        this.personState = personState;
    }

    public PersonState getPersonState() {
        return this.personState;
    }

    public String getFio() {
        return this.fio;
    }
}
