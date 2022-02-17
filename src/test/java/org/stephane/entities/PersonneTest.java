package org.stephane.entities;


import org.junit.jupiter.api.Test;
import org.stephane.excel.business.JavaReflection;
import org.stephane.excel.exceptions.ExcelException;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.BDDAssertions.then;

class PersonneTest {
   private  JavaReflection javaReflection = new JavaReflection();
    @Test
    void create_NewInstance() throws ExcelException {

        Personne personne = javaReflection.getNewInstance(Personne.class);
        then(personne).isNotNull();
    }

    @Test
    void setterField() throws  ExcelException {
        Personne personne = javaReflection.getNewInstance(Personne.class);
        String name = "name";
        String company = "company";
        String address = "address";
        String postalZip = "postalZip";
        String city = "city";
        String guid = "guid";

        javaReflection.setterField(personne, name, name);
        javaReflection.setterField(personne, company, company);
        javaReflection.setterField(personne, address, address);
        javaReflection.setterField(personne, postalZip, postalZip);
        javaReflection.setterField(personne, city, city);
        javaReflection.setterField(personne, guid, guid);

        then(personne.getName()).hasToString(name);
        then(personne.getCompany()).hasToString(company);
        then(personne.getAddress()).hasToString(address);
        then(personne.getPostalZip()).hasToString(postalZip);
        then(personne.getCity()).hasToString(city);
        then(personne.getGuid()).hasToString(guid);

    }


}
