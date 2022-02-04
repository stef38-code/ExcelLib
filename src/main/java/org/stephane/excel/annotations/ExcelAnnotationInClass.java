package org.stephane.excel.annotations;

import java.lang.reflect.Field;
import java.util.List;

public class ExcelAnnotationInClass extends AnnotationInClass {
    private final Class<ExcelSheet> excelSheetClass = ExcelSheet.class;
    private final Class<ExcelDataHeader> excelDataHeaderClass = ExcelDataHeader.class;
    private final Class<ExcelCell> excelCellClass = ExcelCell.class;

    public <T> boolean isExcelSheetAnnotationPresent(Class<T> tClass) {
        return isAnnotationPresent(tClass, excelSheetClass);
    }

    public <T> ExcelSheet getExcelSheetAnnotationValue(Class<T> tClass) {
        return getClassAnnotation(tClass, excelSheetClass);
    }

    public <T> boolean isExcelDataHeaderAnnotationPresent(Class<T> tClass) {
        return isAnnotationPresent(tClass, excelDataHeaderClass);
    }

    public <T> ExcelDataHeader getExcelDataHeaderAnnotationValue(Class<T> tClass) {
        return getClassAnnotation(tClass, excelDataHeaderClass);
    }

    public <T> boolean isExcelCellAnnotationPresent(Class<T> tClass) {
        return isFieldAnnotationPresent(tClass, excelCellClass);
    }

    public <T> List<Field> getFieldContainsExcelCellAnnotationValues(Class<T> tClass) {
        return getFieldContainAnnotation(tClass,excelCellClass);
    }
}
