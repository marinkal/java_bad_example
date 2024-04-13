package com.company;

import java.lang.reflect.Field;;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Main {
    private static ArrayList<String> validate(Person person, PersonState state) {
        ArrayList<String> errors = new ArrayList<String>();
        for (String check_field: state.fields) {
            try { // Сперва пытаемся вызвать такое метод
                Method someMethod =person.getClass().getDeclaredMethod(check_field);
                someMethod.setAccessible(true); //а вдруг приватный
                boolean value = (Boolean) someMethod.invoke(person);
                if (!value) {
                    errors.add("Метод " + check_field + " должен вернуть истину");
                }
            } catch (NoSuchMethodException e) {
                Field field = null; //получаем поле по названию из массива
                try {
                    field = person.getClass().getDeclaredField(check_field);
                    field.setAccessible(true); // варварски получаем доступ к приватному полю
                    Object fieldValue = field.get(person); //получаем значения поля, тип не знаем поэтому объявлен Object
                    String fieldType = field.getType().getName(); //получаем тип поля
                    if (fieldValue == null) {
                        errors.add("Значение поля " + check_field + " не может быть null");
                    } else if (fieldType == "int") {
                        if ((Integer) fieldValue < 18) {
                            errors.add("Значение поля " + check_field + " должно быть от 18 и больше");
                        }
                    } else if (fieldType == "boolean" && !(Boolean) fieldValue) {
                        errors.add("Значение поля " + check_field + " должно быть Истина");
                    }
                } catch (NoSuchFieldException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return errors;
    }

    public static void main(String[] args) {
        Person marina = new Person("Марина Александровна Л", 33);
        Person ekaterina = new Person("Екатерина Павловна Ч", 39);
        Person michael = new Person("Михаил Петрович П", 13);

        ekaterina.setDriverLicense(true);
        ekaterina.setMarriedStatus(true);

        Person[] people = {marina, ekaterina, michael};
        for (Person person : people) {
            //выбирать президента могут только в 18+, поэтому Михаил не сможет
            ArrayList<String> errors = validate(person, PersonState.CHOOSING_THE_PRESIDENT);
            if (errors.size() > 0) {
                System.out.println(person.getFio() + " не может выбирать президента потому что: " + errors);
            } else {
                System.out.println(person.getFio() + " может выбирать президента");
                person.setPersonState(PersonState.CHOOSING_THE_PRESIDENT);
            }
            //водить машину сможет только Екатерина
            errors = validate(person, PersonState.DRIVING_A_CAR);
            if (errors.size() > 0) {
                System.out.println(person.getFio() + " не может водить машину потому что: " + errors);
            } else {
                System.out.println(person.getFio() + " может водить машину");
                person.setPersonState(PersonState.DRIVING_A_CAR);
            }
            // регистрировать брак может только Марина
            errors = validate(person, PersonState.GETTING_MARRIED);
            if (errors.size() > 0) {
                System.out.println(person.getFio() + " не может регистрировать брак потому что: " + errors);
            } else {
                System.out.println(person.getFio() + " может регистрировать брак");
                person.setPersonState(PersonState.GETTING_MARRIED);
            }
            // Михаил слишком мал чтобы работать
            errors = validate(person, PersonState.HAS_AN_OFFICIAL_JOB);
            if (errors.size() > 0) {
                System.out.println(person.getFio() + " не может иметь оф работу потому что: " + errors);
            } else {
                System.out.println(person.getFio() + " может иметь оф работу");
                person.setPersonState(PersonState.HAS_AN_OFFICIAL_JOB);
            }
            System.out.println("=========");
        }
    }


}