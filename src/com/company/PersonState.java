package com.company;

// список состояний - с массивами полей которые надо проверять для перехода в это состояеме

public enum PersonState {
    NOTHING (new String[]{}),
    CHOOSING_THE_PRESIDENT (new String[]{"age", "isRussianCitizen"}),
    DRIVING_A_CAR (new String[]{"age", "hasDriverLicense"});

    public String[] fields;

    PersonState(String[] fields) {
        this.fields = fields;
    }


}