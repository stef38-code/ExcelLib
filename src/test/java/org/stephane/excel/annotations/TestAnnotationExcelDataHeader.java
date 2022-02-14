package org.stephane.excel.annotations;

import org.junit.jupiter.api.Test;
import org.stephane.entities.Personne;

import static org.assertj.core.api.BDDAssertions.then;

class TestAnnotationExcelDataHeader {

    @Test
    void isExcelDataHeaderAnnotationPresent_Class_ExcelDataHeader() {
        then(ExcelAnnotationInClass.isExcelDataHeaderAnnotationPresent(Personne.class)).isTrue();
        then(ExcelAnnotationInClass.isExcelDataHeaderAnnotationPresent(First.class)).isTrue();
        then(ExcelAnnotationInClass.isExcelDataHeaderAnnotationPresent(Second.class)).isTrue();
        then(ExcelAnnotationInClass.isExcelDataHeaderAnnotationPresent(Third.class)).isFalse();
    }

    @Test
    void getExcelDataHeaderAnnotationValue_ExcelDataHeaderAnnotation() {
        ExcelDataHeader ExcelDataHeaderPersonne = ExcelAnnotationInClass.getExcelDataHeaderAnnotationValue(Personne.class);
        thenExcelSheet(ExcelDataHeaderPersonne, 0);
        ExcelDataHeader ExcelDataHeaderFirst = ExcelAnnotationInClass.getExcelDataHeaderAnnotationValue(First.class);
        thenExcelSheet(ExcelDataHeaderFirst, 1);

        ExcelDataHeader ExcelDataHeaderSecond = ExcelAnnotationInClass.getExcelDataHeaderAnnotationValue(Second.class);
        thenExcelSheet(ExcelDataHeaderSecond, 2);


        ExcelDataHeader ExcelDataHeaderThird = ExcelAnnotationInClass.getExcelDataHeaderAnnotationValue(Third.class);
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
