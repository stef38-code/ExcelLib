package org.stephane.excel.annotations;

import java.lang.annotation.Annotation;

public class AnalyseExcelAnnotationInClass {
    private Class<ExcelSheet> aClass = ExcelSheet.class;

    public <T> boolean isExcelSheetAnnotationPresent(Class<T> tClass) {
        return isAnnotationPresent(tClass, aClass);
    }

    public <T> ExcelSheet getExcelSheetAnnotationValue(Class<T> tClass) {
        return getClassAnnotation(tClass, aClass);
    }

    private <T, A extends Annotation> boolean isAnnotationPresent(Class<T> tClass, Class<A> aClass) {
        return tClass.isAnnotationPresent(aClass);
    }

    private <T, A extends Annotation> A getClassAnnotation(Class<T> tClass, Class<A> aClass) {
        return tClass.getAnnotation(aClass);
    }
}
