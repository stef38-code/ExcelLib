package org.stephane.excel.business;

import org.stephane.excel.annotations.business.ExcelAnnotationInClass;
import org.stephane.excel.parser.entities.EntityDefinition;
import org.stephane.excel.parser.entities.EntityDefinitionBuilder;

public class AnalyseClass {
    private AnalyseClass() {
    }

    public static <T> EntityDefinition check(Class<T> tClass) {
        EntityDefinitionBuilder entityDefinitionBuilder = EntityDefinitionBuilder.create();
        checkExcelSheet(tClass, entityDefinitionBuilder);
        checkExcelDataHeader(tClass, entityDefinitionBuilder);
        checkExcelCell(tClass, entityDefinitionBuilder);
        return entityDefinitionBuilder.build();
    }

    private static <T> void checkExcelCell(Class<T> tClass, EntityDefinitionBuilder entityDefinitionBuilder) {
        if (ExcelAnnotationInClass.isExcelCellAnnotationPresent(tClass)) {
            entityDefinitionBuilder.withFields(ExcelAnnotationInClass.getFieldContainsExcelCellAnnotationValues(tClass));
        }
    }

    private static <T> void checkExcelDataHeader(Class<T> tClass, EntityDefinitionBuilder entityDefinitionBuilder) {
        if (ExcelAnnotationInClass.isExcelDataHeaderAnnotationPresent(tClass)) {
            entityDefinitionBuilder.withExcelDataHeader(ExcelAnnotationInClass.getExcelDataHeaderAnnotationValue(tClass));
        }
    }

    private static <T> void checkExcelSheet(Class<T> tClass, EntityDefinitionBuilder entityDefinitionBuilder) {
        if (ExcelAnnotationInClass.isExcelSheetAnnotationPresent(tClass)) {
            entityDefinitionBuilder.withExcelSheet(ExcelAnnotationInClass.getExcelSheetAnnotationValue(tClass));
        }
    }
}
