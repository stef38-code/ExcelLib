package org.stephane.excel.annotations;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.stephane.entities.Personne;

import static org.assertj.core.api.BDDAssertions.then;

class TestAnnotationExcelSheet {

    @Test
    void isExcelSheetAnnotationPresent_Class_ExcelSheet() {
        then(ExcelAnnotationInClass.isExcelSheetAnnotationPresent(Personne.class)).isTrue();
        then(ExcelAnnotationInClass.isExcelSheetAnnotationPresent(First.class)).isTrue();
        then(ExcelAnnotationInClass.isExcelSheetAnnotationPresent(Second.class)).isTrue();
        then(ExcelAnnotationInClass.isExcelSheetAnnotationPresent(Third.class)).isTrue();
        then(ExcelAnnotationInClass.isExcelSheetAnnotationPresent(Fourth.class)).isTrue();
        then(ExcelAnnotationInClass.isExcelSheetAnnotationPresent(Fifth.class)).isFalse();
    }

    @Test
    void getExcelSheetAnnotationValue_ExcelSheetAnnotation() {
        ExcelSheet ExcelSheetPersonne = ExcelAnnotationInClass.getExcelSheetAnnotationValue(Personne.class);
        thenExcelSheet(ExcelSheetPersonne, 0, "Feuil1");
        ExcelSheet ExcelSheetFirst = ExcelAnnotationInClass.getExcelSheetAnnotationValue(First.class);
        thenExcelSheet(ExcelSheetFirst, 0, StringUtils.EMPTY);

        ExcelSheet ExcelSheetSecond = ExcelAnnotationInClass.getExcelSheetAnnotationValue(Second.class);
        thenExcelSheet(ExcelSheetSecond, 2, StringUtils.EMPTY);


        ExcelSheet ExcelSheetThird = ExcelAnnotationInClass.getExcelSheetAnnotationValue(Third.class);
        thenExcelSheet(ExcelSheetThird, 0, "Feuil1");

        ExcelSheet ExcelSheetFourth = ExcelAnnotationInClass.getExcelSheetAnnotationValue(Fourth.class);
        thenExcelSheet(ExcelSheetFourth, 3, "Feuil1");

        ExcelSheet ExcelSheetFifth = ExcelAnnotationInClass.getExcelSheetAnnotationValue(Fifth.class);
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

    private static class Fifth {

    }

}
