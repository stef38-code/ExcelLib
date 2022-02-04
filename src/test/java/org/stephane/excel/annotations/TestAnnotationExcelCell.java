package org.stephane.excel.annotations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stephane.excel.entities.Personne;

import java.lang.reflect.Field;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class TestAnnotationExcelCell {
    private ExcelAnnotationInClass excelAnnotationInClass;

    @BeforeEach
    void setUp() {
        excelAnnotationInClass = new ExcelAnnotationInClass();
    }

    @Test
    void isExcelCellAnnotationPresent_Class_ExcelCell() {
        then(excelAnnotationInClass.isExcelCellAnnotationPresent(Personne.class)).isTrue();
        then(excelAnnotationInClass.isExcelCellAnnotationPresent(First.class)).isFalse();
    }

    @Test
    void getListExcelCellAnnotationValue_ExcelCellAnnotation() {
        List<Field> listFieldOfPersonne = excelAnnotationInClass.getFieldContainsExcelCellAnnotationValues(Personne.class);
        List<Field> listFieldOfFirst = excelAnnotationInClass.getFieldContainsExcelCellAnnotationValues(First.class);

        then(listFieldOfPersonne).isNotEmpty().hasSize(6);
        then(listFieldOfFirst).isEmpty();

    }

    private static class First {

    }
}
