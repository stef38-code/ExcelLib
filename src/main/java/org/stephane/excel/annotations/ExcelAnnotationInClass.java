package org.stephane.excel.annotations;

import java.lang.reflect.Field;
import java.util.List;

public class ExcelAnnotationInClass extends AnnotationInClass {
    private static final Class<ExcelSheet> EXCEL_SHEET_CLASS = ExcelSheet.class;
    private static final Class<ExcelDataHeader> EXCEL_DATA_HEADER_CLASS = ExcelDataHeader.class;
    private static final Class<ExcelCell> EXCEL_CELL_CLASS = ExcelCell.class;

    public <T> boolean isExcelSheetAnnotationPresent(Class<T> tClass) {
        return isAnnotationPresent(tClass, EXCEL_SHEET_CLASS);
    }

    public <T> ExcelSheet getExcelSheetAnnotationValue(Class<T> tClass) {
        return getClassAnnotation(tClass, EXCEL_SHEET_CLASS);
    }

    public <T> boolean isExcelDataHeaderAnnotationPresent(Class<T> tClass) {
        return isAnnotationPresent(tClass, EXCEL_DATA_HEADER_CLASS);
    }

    public <T> ExcelDataHeader getExcelDataHeaderAnnotationValue(Class<T> tClass) {
        return getClassAnnotation(tClass, EXCEL_DATA_HEADER_CLASS);
    }

    public <T> boolean isExcelCellAnnotationPresent(Class<T> tClass) {
        return isFieldAnnotationPresent(tClass, EXCEL_CELL_CLASS);
    }

    public <T> List<Field> getFieldContainsExcelCellAnnotationValues(Class<T> tClass) {
        return getFieldContainAnnotation(tClass,EXCEL_CELL_CLASS);
    }
}
