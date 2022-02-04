package org.stephane.excel.parser;

import lombok.Getter;
import org.stephane.excel.annotations.ExcelAnnotationInClass;

import java.util.Optional;

public class AnalyseClass {
    ExcelAnnotationInClass excelAnnotationInClass = new ExcelAnnotationInClass();
    @Getter
    EntityDefinition entityDefinition;

    public <T> EntityDefinition check(Class<T> tClass) {
        entityDefinition = new EntityDefinition();
        checkExcelSheet(tClass);
        checkExcelDataHeader(tClass);
        checkExcelCell(tClass);
        return getEntityDefinition();
    }

    private <T> void checkExcelCell(Class<T> tClass) {
        if (excelAnnotationInClass.isExcelCellAnnotationPresent(tClass)) {
            entityDefinition.setFields(Optional.ofNullable(excelAnnotationInClass.getFieldContainsExcelCellAnnotationValues(tClass)));
        }
    }

    private <T> void checkExcelDataHeader(Class<T> tClass) {
        if (excelAnnotationInClass.isExcelDataHeaderAnnotationPresent(tClass)) {
            entityDefinition.setExcelDataHeader(Optional.ofNullable(excelAnnotationInClass.getExcelDataHeaderAnnotationValue(tClass)));
        }
    }

    private <T> void checkExcelSheet(Class<T> tClass) {
        if (excelAnnotationInClass.isExcelSheetAnnotationPresent(tClass)) {
            entityDefinition.setExcelSheet(Optional.ofNullable(excelAnnotationInClass.getExcelSheetAnnotationValue(tClass)));
        }
    }
}
