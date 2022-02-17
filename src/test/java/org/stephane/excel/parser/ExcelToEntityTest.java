package org.stephane.excel.parser;

import lombok.Getter;
import lombok.Setter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stephane.entities.Chien;
import org.stephane.entities.Personne;
import org.stephane.excel.annotations.ExcelCell;
import org.stephane.excel.annotations.ExcelDataHeader;
import org.stephane.excel.annotations.ExcelSheet;
import org.stephane.excel.exceptions.ExcelException;
import org.stephane.excel.tools.FileUtil;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

class ExcelToEntityTest {
    ExcelToEntity excelToEntity;
    String fExcel;

    @BeforeEach
    void setUp() {
        excelToEntity = new ExcelToEntity();
        fExcel = FileUtil.getAbsolutePath("exemple.xls");
    }

    @Test
    void parse_numberHeaderSeven_entityPersonne() throws ExcelException {
        List<Personne3> personnes = excelToEntity.parse(fExcel, Personne3.class);
        then(personnes).isNotNull().isNotEmpty();
        personnes.forEach(personne -> {
            then(personne.getName()).isNotNull();
            then(personne.getPostalZip()).isNotNull();
            then(personne.getGuid()).isNotNull();
            then(personne.getAddress()).isNotNull();
            then(personne.getCompany()).isNotNull();
            then(personne.getCity()).isNotNull();
        });
    }

    @Test
    void parse_fileExcel_emptyList() {
        thenThrownBy(() -> excelToEntity.parse(fExcel, Personne2.class))
                .isInstanceOf(ExcelException.class)
                .hasMessage("Annotation not found !!");
    }

    @ExcelSheet
    @ExcelDataHeader
    @Getter
    @Setter
    public static class Personne2 {

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
    public static class Personne3 {
        @ExcelCell
        private String name;
        @ExcelCell(number = 1)
        private String company;
        @ExcelCell(number = 2)
        private String address;
        @ExcelCell(number = 3, stringFormat = true)
        private String postalZip;
        @ExcelCell(number = 4)
        private String city;
        @ExcelCell(number = 5)
        private String guid;

        public Personne3() {
        }
    }
}
