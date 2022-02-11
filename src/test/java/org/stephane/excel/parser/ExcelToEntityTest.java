package org.stephane.excel.parser;

import lombok.Getter;
import lombok.Setter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stephane.excel.ExcelException;
import org.stephane.excel.annotations.ExcelCell;
import org.stephane.excel.annotations.ExcelDataHeader;
import org.stephane.excel.annotations.ExcelSheet;
import org.stephane.excel.entities.Chien;
import org.stephane.excel.entities.Personne;
import org.stephane.excel.tools.FileUtil;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class ExcelToEntityTest {
    ExcelToEntity excelToEntity;
    String fExcel;

    @BeforeEach
    void setUp() {
        excelToEntity = new ExcelToEntity();
        fExcel = FileUtil.getAbsolutePath("exemple.xls");
    }

    @Test
    void parse_fileExcel_entityPersonne() throws ExcelException {
        List<Personne> personnes = excelToEntity.parse(fExcel, Personne.class);
        then(personnes).isNotNull().isNotEmpty();
        personnes.forEach( personne -> assertField(personne));
    }
    @Test
    void parse_numberHeaderSeven_entityPersonne() throws ExcelException {
        List<Personne3> personnes = excelToEntity.parse(fExcel, Personne3.class);
        then(personnes).isNotNull().isNotEmpty();
        personnes.forEach( personne -> {
            Assertions.assertThat(personne.getName()).isNotNull();
            Assertions.assertThat(personne.getPostalZip()).isNotNull();
            Assertions.assertThat(personne.getGuid()).isNotNull();
            Assertions.assertThat(personne.getAddress()).isNotNull();
            Assertions.assertThat(personne.getCompany()).isNotNull();
            Assertions.assertThat(personne.getCity()).isNotNull();
        });
    }
    @Test
    void parse_fileExcel_entityChien() throws ExcelException {
        fExcel = FileUtil.getAbsolutePath("dogregistration2.xls");
        List<Chien> chiens = excelToEntity.parse(fExcel, Chien.class);
        then(chiens).isNotNull().isNotEmpty();
    }
    private void assertField(Personne personne) {
        Assertions.assertThat(personne.getName()).isNotNull();
        Assertions.assertThat(personne.getPostalZip()).isNotNull();
        Assertions.assertThat(personne.getGuid()).isNotNull();
        Assertions.assertThat(personne.getAddress()).isNotNull();
        Assertions.assertThat(personne.getCompany()).isNotNull();
        Assertions.assertThat(personne.getCity()).isNotNull();
    }

    @Test
    void parse_fileExcel_emptyList() throws ExcelException {
        List<Personne2> personnes = excelToEntity.parse(fExcel, Personne2.class);
        then(personnes).isNotNull().isEmpty();
    }

    @ExcelSheet
    @ExcelDataHeader
    @Getter
    @Setter
    private static class Personne2 {

        private String name;

        private String company;

        private String address;

        private String postalZip;

        private String city;

        private String guid;
    }

    @ExcelSheet(name = "feuil2")
    @ExcelDataHeader(rowNumber = 8)
    @Getter
    @Setter
    private static class Personne3 {
        public Personne3() {
        }

        @ExcelCell
        private String name;
        @ExcelCell(number =1)
        private String company;
        @ExcelCell(number =2)
        private String address;
        @ExcelCell(number =3, stringFormat = true)
        private String postalZip;
        @ExcelCell(number =4)
        private String city;
        @ExcelCell(number =5)
        private String guid;
    }
}
