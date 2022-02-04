package org.stephane.excel.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stephane.excel.ExcelException;
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
    void parse_fileExcel_entity() throws ExcelException {
        List<Personne> personnes = excelToEntity.parse(fExcel, Personne.class);
        then(personnes).isNotNull();
    }
}
