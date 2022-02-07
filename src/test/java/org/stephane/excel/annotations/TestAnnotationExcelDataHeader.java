package org.stephane.excel.annotations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stephane.excel.entities.Personne;

import static org.assertj.core.api.BDDAssertions.then;

class TestAnnotationExcelDataHeader {
    private ExcelAnnotationInClass excelAnnotationInClass;

    @BeforeEach
    void setUp() {
        excelAnnotationInClass = new ExcelAnnotationInClass();
    }

    @Test
    void isExcelDataHeaderAnnotationPresent_Class_ExcelDataHeader() {
        then(excelAnnotationInClass.isExcelDataHeaderAnnotationPresent(Personne.class)).isTrue();
        then(excelAnnotationInClass.isExcelDataHeaderAnnotationPresent(First.class)).isTrue();
        then(excelAnnotationInClass.isExcelDataHeaderAnnotationPresent(Second.class)).isTrue();
        then(excelAnnotationInClass.isExcelDataHeaderAnnotationPresent(Third.class)).isFalse();
    }

    @Test
    void getExcelDataHeaderAnnotationValue_ExcelDataHeaderAnnotation() {
        ExcelDataHeader ExcelDataHeaderPersonne = excelAnnotationInClass.getExcelDataHeaderAnnotationValue(Personne.class);
        thenExcelSheet(ExcelDataHeaderPersonne, 0);
        ExcelDataHeader ExcelDataHeaderFirst = excelAnnotationInClass.getExcelDataHeaderAnnotationValue(First.class);
        thenExcelSheet(ExcelDataHeaderFirst, 1);

        ExcelDataHeader ExcelDataHeaderSecond = excelAnnotationInClass.getExcelDataHeaderAnnotationValue(Second.class);
        thenExcelSheet(ExcelDataHeaderSecond, 2);


        ExcelDataHeader ExcelDataHeaderThird = excelAnnotationInClass.getExcelDataHeaderAnnotationValue(Third.class);
        then(ExcelDataHeaderThird).isNull();

    }

    private void thenExcelSheet(ExcelDataHeader excelDataHeader, int number) {
        then(excelDataHeader).isNotNull();
        then(excelDataHeader.rowNumber()).isEqualTo(number);
    }


    @ExcelDataHeader
    private static class First {

    }

    @ExcelDataHeader(rowNumber = 2)
    private static class Second {

    }

    private static class Third {

    }
}
