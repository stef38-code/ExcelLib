package org.stephane.excel.annotations;

import org.junit.jupiter.api.Test;
import org.stephane.entities.Personne;

import java.lang.reflect.Field;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class TestAnnotationExcelCell {


    @Test
    void isExcelCellAnnotationPresent_Class_ExcelCell() {
        then(ExcelAnnotationInClass.isExcelCellAnnotationPresent(Personne.class)).isTrue();
        then(ExcelAnnotationInClass.isExcelCellAnnotationPresent(First.class)).isFalse();
    }

    @Test
    void getListExcelCellAnnotationValue_ExcelCellAnnotation() {
        List<Field> listFieldOfPersonne = ExcelAnnotationInClass.getFieldContainsExcelCellAnnotationValues(Personne.class);
        List<Field> listFieldOfFirst = ExcelAnnotationInClass.getFieldContainsExcelCellAnnotationValues(First.class);

        then(listFieldOfPersonne).isNotEmpty().hasSize(6);
        then(listFieldOfFirst).isEmpty();

    }

    private static class First {

    }
}
