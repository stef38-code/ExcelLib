package org.stephane.excel.annotations.business;

import org.stephane.excel.annotations.ExcelCell;
import org.stephane.excel.annotations.ExcelDataHeader;
import org.stephane.excel.annotations.ExcelSheet;

import java.lang.reflect.Field;
import java.util.List;

public class ExcelAnnotationInClass  {
    private ExcelAnnotationInClass() {
    }

    private static final Class<ExcelSheet> EXCEL_SHEET_CLASS = ExcelSheet.class;
    private static final Class<ExcelDataHeader> EXCEL_DATA_HEADER_CLASS = ExcelDataHeader.class;
    private static final Class<ExcelCell> EXCEL_CELL_CLASS = ExcelCell.class;

    public static <T> boolean isExcelSheetAnnotationPresent(Class<T> tClass) {
        return AnnotationInClass.isAnnotationPresent(tClass, EXCEL_SHEET_CLASS);
    }

    public static <T> ExcelSheet getExcelSheetAnnotationValue(Class<T> tClass) {
        return AnnotationInClass.getClassAnnotation(tClass, EXCEL_SHEET_CLASS);
    }

    public static <T> boolean isExcelDataHeaderAnnotationPresent(Class<T> tClass) {
        return AnnotationInClass.isAnnotationPresent(tClass, EXCEL_DATA_HEADER_CLASS);
    }

    public static <T> ExcelDataHeader getExcelDataHeaderAnnotationValue(Class<T> tClass) {
        return AnnotationInClass.getClassAnnotation(tClass, EXCEL_DATA_HEADER_CLASS);
    }

    public static <T> boolean isExcelCellAnnotationPresent(Class<T> tClass) {
        return AnnotationInClass.isFieldAnnotationPresent(tClass, EXCEL_CELL_CLASS);
    }

    public static <T> List<Field> getFieldContainsExcelCellAnnotationValues(Class<T> tClass) {
        return AnnotationInClass.getFieldContainAnnotation(tClass,EXCEL_CELL_CLASS);
    }
}
