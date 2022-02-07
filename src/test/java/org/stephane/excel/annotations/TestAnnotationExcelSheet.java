package org.stephane.excel.annotations;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stephane.excel.entities.Personne;

import static org.assertj.core.api.BDDAssertions.then;

class TestAnnotationExcelSheet {
    private ExcelAnnotationInClass excelAnnotationInClass;

    @BeforeEach
    void setUp() {
        excelAnnotationInClass = new ExcelAnnotationInClass();
    }

    @Test
    void isExcelSheetAnnotationPresent_Class_ExcelSheet() {
        then(excelAnnotationInClass.isExcelSheetAnnotationPresent(Personne.class)).isTrue();
        then(excelAnnotationInClass.isExcelSheetAnnotationPresent(First.class)).isTrue();
        then(excelAnnotationInClass.isExcelSheetAnnotationPresent(Second.class)).isTrue();
        then(excelAnnotationInClass.isExcelSheetAnnotationPresent(Third.class)).isTrue();
        then(excelAnnotationInClass.isExcelSheetAnnotationPresent(Fourth.class)).isTrue();
        then(excelAnnotationInClass.isExcelSheetAnnotationPresent(Fifth.class)).isFalse();
    }

    @Test
    void getExcelSheetAnnotationValue_ExcelSheetAnnotation() {
        ExcelSheet ExcelSheetPersonne = excelAnnotationInClass.getExcelSheetAnnotationValue(Personne.class);
        thenExcelSheet(ExcelSheetPersonne, 0, StringUtils.EMPTY);
        ExcelSheet ExcelSheetFirst = excelAnnotationInClass.getExcelSheetAnnotationValue(First.class);
        thenExcelSheet(ExcelSheetFirst, 0, StringUtils.EMPTY);

        ExcelSheet ExcelSheetSecond = excelAnnotationInClass.getExcelSheetAnnotationValue(Second.class);
        thenExcelSheet(ExcelSheetSecond, 2, StringUtils.EMPTY);


        ExcelSheet ExcelSheetThird = excelAnnotationInClass.getExcelSheetAnnotationValue(Third.class);
        thenExcelSheet(ExcelSheetThird, 0, "Feuil1");

        ExcelSheet ExcelSheetFourth = excelAnnotationInClass.getExcelSheetAnnotationValue(Fourth.class);
        thenExcelSheet(ExcelSheetFourth, 3, "Feuil1");

        ExcelSheet ExcelSheetFifth = excelAnnotationInClass.getExcelSheetAnnotationValue(Fifth.class);
        then(ExcelSheetFifth).isNull();
    }

    private void thenExcelSheet(ExcelSheet ExcelSheetFirst, int number, String name) {
        then(ExcelSheetFirst).isNotNull();
        then(ExcelSheetFirst.number()).isEqualTo(number);
        then(ExcelSheetFirst.name()).isEqualTo(name);
    }


    @ExcelSheet
    private static class First {

    }

    @ExcelSheet(number = 2)
    private static class Second {

    }

    @ExcelSheet(name = "Feuil1")
    private static class Third {

    }

    @ExcelSheet(number = 3, name = "Feuil1")
    private static class Fourth {

    }

    private class Fifth {

    }

}
