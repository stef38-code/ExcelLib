package org.stephane.excel.annotations;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stephane.excel.Personne;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;

public class TestAnnotationExcelSheet {
    private AnalyseExcelAnnotationInClass analyseExcelAnnotationInClass;

    @BeforeEach
    void setUp() {
        analyseExcelAnnotationInClass = new AnalyseExcelAnnotationInClass();
    }

    @Test
    void isExcelSheetAnnotationPresent_Class_ExcelSheet() throws NoSuchFieldException {
        then(analyseExcelAnnotationInClass.isExcelSheetAnnotationPresent(Personne.class)).isTrue();
        then(analyseExcelAnnotationInClass.isExcelSheetAnnotationPresent(First.class)).isTrue();
        then(analyseExcelAnnotationInClass.isExcelSheetAnnotationPresent(Second.class)).isTrue();
        then(analyseExcelAnnotationInClass.isExcelSheetAnnotationPresent(Third.class)).isTrue();
        then(analyseExcelAnnotationInClass.isExcelSheetAnnotationPresent(Fourth.class)).isTrue();
        then(analyseExcelAnnotationInClass.isExcelSheetAnnotationPresent(Fifth.class)).isFalse();
    }

    @Test
    void getExcelSheetAnnotationValue_ExcelSheetAnnotation() throws NoSuchFieldException {
        ExcelSheet ExcelSheetPersonne = analyseExcelAnnotationInClass.getExcelSheetAnnotationValue(Personne.class);
        thenExcelSheet(ExcelSheetPersonne, 1, StringUtils.EMPTY);
        ExcelSheet ExcelSheetFirst = analyseExcelAnnotationInClass.getExcelSheetAnnotationValue(First.class);
        thenExcelSheet(ExcelSheetFirst, 1, StringUtils.EMPTY);

        ExcelSheet ExcelSheetSecond = analyseExcelAnnotationInClass.getExcelSheetAnnotationValue(Second.class);
        thenExcelSheet(ExcelSheetSecond, 2, StringUtils.EMPTY);


        ExcelSheet ExcelSheetThird = analyseExcelAnnotationInClass.getExcelSheetAnnotationValue(Third.class);
        thenExcelSheet(ExcelSheetThird, 1, "Feuil1");

        ExcelSheet ExcelSheetFourth = analyseExcelAnnotationInClass.getExcelSheetAnnotationValue(Fourth.class);
        thenExcelSheet(ExcelSheetFourth, 3, "Feuil1");

        ExcelSheet ExcelSheetFifth = analyseExcelAnnotationInClass.getExcelSheetAnnotationValue(Fifth.class);
        then(ExcelSheetFifth).isNull();
    }

    private void thenExcelSheet(ExcelSheet ExcelSheetFirst, int number, String name) {
        then(ExcelSheetFirst).isNotNull();
        then(ExcelSheetFirst.number()).isEqualTo(number);
        then(ExcelSheetFirst.name()).isEqualTo(name);
    }


    private class AnalyseExcelAnnotationInClass extends org.stephane.excel.annotations.AnalyseExcelAnnotationInClass {
        private <T, A extends Annotation> List<Field> getFieldContainAnnotation(Class<T> tClass, Class<A> aClass) {
            return Stream.of(tClass.getDeclaredFields())
                    .filter(field -> field.getAnnotation(aClass) != null)
                    .collect(Collectors.toList());
        }
    }

    @ExcelSheet
    private class First {
        private String name;
    }

    @ExcelSheet(number = 2)
    private class Second {
        private String name;
    }

    @ExcelSheet(name = "Feuil1")
    private class Third {
        private String name;
    }

    @ExcelSheet(number = 3, name = "Feuil1")
    private class Fourth {
        private String name;
    }

    private class Fifth {
        private String name;
    }

}
