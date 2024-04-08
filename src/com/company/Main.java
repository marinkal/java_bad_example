package com.company;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static String[] validate(Person person, PersonState state) {
        String[] errros = new String[3000]; // нз как в java сделать по нормальному
        int index = 0;
       // System.out.println(Arrays.toString(state.fields));
        for (String check_field: state.fields) {
            System.out.println(check_field);
            try {
                Field field = person.getClass().getDeclaredField(check_field); //получаем поле по названию из массива
                field.setAccessible(true); // варварски получаем доступ к приватному полю
                Object value = field.get(person);
                String fieldType = field.getType().getName();
                if (value == null) {
                    errros[index] = "Значение поля " + check_field + " не может быть null";
                    index++;
                } else if (fieldType == "int") {// поле типа int - проверяем что больше 18

                }

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

//            field.setAccessible(true); // варварски делаем приватное поле доступным
//            Object value = field.get(person);
//            System.out.println(value);
        }
        return errros;
    }

    public static void main(String[] args) {
        Person marina = new Person("Марина Александровна Л", 33);
        Person ekaterina = new Person("Екатерина Павлновна Ч", 39);
        Person michael = new Person("Михаил Петрович П", 13);

        ekaterina.setDriverLicense(true);
        ekaterina.setMarriedStatus(true);

        Person[] people = {marina, ekaterina, michael};
        for (Person person : people) {
            //никто не сможет водить машину, потому что никому не выданы права
            //выбирать президента могут только в 18+
            String[] errors = validate(person, PersonState.CHOOSING_THE_PRESIDENT);
            if (errors[0] != "") {
                System.out.println(person.getFio() + " не может выбирать президента потому что:");
                System.out.println(Arrays.toString(errors));
            } else {
                System.out.println(person.getFio() + " может выбирать президента");
                person.setPersonState(PersonState.CHOOSING_THE_PRESIDENT);
            }
        }
    }


}