package org.stephane.excel.business;

import org.junit.jupiter.api.Test;
import org.stephane.excel.ExcelException;
import org.stephane.entities.Personne;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

class JavaReflectionTest {
    @Test
    void create_NewInstance() throws ExcelException {
        JavaReflection javaReflection = new JavaReflection();
        Personne personne = javaReflection.getNewInstance(Personne.class);
        javaReflection.setterField(personne, "name", "name");
        javaReflection.setterField(personne, "company", "company");
        javaReflection.setterField(personne, "address", "address");
        javaReflection.setterField(personne, "postalZip", "postalZip");
        javaReflection.setterField(personne, "city", "city");
        javaReflection.setterField(personne, "guid", "guid");

        then(personne.getName()).hasToString("name");
        then(personne.getCompany()).hasToString("company");
        then(personne.getAddress()).hasToString("address");
        then(personne.getPostalZip()).hasToString("postalZip");
        then(personne.getCity()).hasToString("city");
        then(personne.getGuid()).hasToString("guid");
    }
    @Test
    void exception() throws ExcelException {
        JavaReflection javaReflection = new JavaReflection();
        Personne personne = javaReflection.getNewInstance(Personne.class);
        assertThatThrownBy(()->{
            javaReflection.setterField(personne, "Bidon", "name");
        }).isInstanceOf(ExcelException.class);
        assertThatThrownBy(()->{
            javaReflection.setterField(personne, "name", 123.00);
        }).isInstanceOf(ExcelException.class);
    }
}
