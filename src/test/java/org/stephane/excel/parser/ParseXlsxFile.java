package org.stephane.excel.parser;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stephane.entities.Personne;
import org.stephane.excel.exceptions.ExcelException;
import org.stephane.excel.tools.FileUtil;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class ParseXlsxFile {
    private ExcelToEntity excelToEntity;

    @BeforeEach
    void setUp() {
        excelToEntity = new ExcelToEntity();
    }

    @Test
    void parse_fileExcel_entityPersonne() throws ExcelException {
        String fExcel = FileUtil.getAbsolutePath("exemple.xlsx");
        List<Personne> personnes = excelToEntity.parse(fExcel, Personne.class);
        then(personnes).isNotNull().isNotEmpty().hasSize(30);
        personnes.forEach(this::thenPersonneFieldNotNull);
        thenSpecificField(personnes.get(0), "Kelsie Knowles", "Ut Pellentesque Eget Inc.", "P.O. Box 206, 4536 In Road", "62860.0", "Saint-Louis", "1503737E-17EF-4B3E-3B9F-3B73863350DB");
        thenSpecificField(personnes.get(15), "Dane Kidd", "Molestie Company", "P.O. Box 208, 3812 Dolor, Rd.", "62824.0", "Limoges", "6EC185EB-3185-4C5E-413E-E45DDB4CA6FF");
        thenSpecificField(personnes.get(personnes.size() - 1), "Quynn Cox", "Bibendum Sed Associates", "Ap #868-4434 Eget Rd.", "21858.0", "Boulogne-sur-Mer", "CAE76A63-DA3C-09D7-7B11-C9DBA9FA310A");

    }

    private void thenPersonneFieldNotNull(Personne personne) {
        Assertions.assertThat(personne.getName()).isNotNull();
        Assertions.assertThat(personne.getPostalZip()).isNotNull();
        Assertions.assertThat(personne.getGuid()).isNotNull();
        Assertions.assertThat(personne.getAddress()).isNotNull();
        Assertions.assertThat(personne.getCompany()).isNotNull();
        Assertions.assertThat(personne.getCity()).isNotNull();
    }

    private void thenSpecificField(Personne personne, String name, String compagny, String address, String postalZip, String city, String guid) {
        then(personne.getName()).hasToString(name);
        then(personne.getPostalZip()).hasToString(postalZip);
        then(personne.getGuid()).hasToString(guid);
        then(personne.getAddress()).hasToString(address);
        then(personne.getCompany()).hasToString(compagny);
        then(personne.getCity()).hasToString(city);
    }
}
