package com.company;

import java.util.ArrayList;

public enum PersonState {
    NOTHING (new String[]{}),
    CHOOSING_THE_PRESIDENT (new String[]{"age", "isRussianCitizen"}),
    DRIVING_A_CAR (new String[]{"age", "hasDriverLicense"});

    public String[] fields;

    PersonState(String[] fields) {
        this.fields = fields;
    }


}