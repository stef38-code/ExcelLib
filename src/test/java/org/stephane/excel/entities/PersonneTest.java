package org.stephane.excel.entities;


import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.BDDAssertions.then;

class PersonneTest {

    @Test
    void create_NewInstance() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Personne personne = getNewInstance(Personne.class);
        then(personne).isNotNull();
    }

    @Test
    void setterField() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        Personne personne = getNewInstance(Personne.class);
        setterField(personne, "name", "name");
        setterField(personne, "company", "company");
        setterField(personne, "address", "address");
        setterField(personne, "postalZip", "postalZip");
        setterField(personne, "city", "city");
        setterField(personne, "guid", "guid");

        then(personne.getName()).hasToString("name");
        then(personne.getCompany()).hasToString("company");
        then(personne.getAddress()).hasToString("address");
        then(personne.getPostalZip()).hasToString("postalZip");
        then(personne.getCity()).hasToString("city");
        then(personne.getGuid()).hasToString("guid");

    }

    private <T> T getNewInstance(Class<T> tclass) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return tclass.getDeclaredConstructor().newInstance();
    }

    private <T> void setterField(T object, String nameField, String value) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(nameField);
        field.setAccessible(true);
        field.set(object, value);
    }
}
