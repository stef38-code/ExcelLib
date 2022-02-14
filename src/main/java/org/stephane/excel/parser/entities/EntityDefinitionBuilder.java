package org.stephane.excel.parser.entities;

import org.stephane.excel.annotations.ExcelDataHeader;
import org.stephane.excel.annotations.ExcelSheet;

import java.lang.reflect.Field;
import java.util.List;

public final class EntityDefinitionBuilder {
    private EntityDefinition entityDefinition;

    private EntityDefinitionBuilder() {
        entityDefinition = new EntityDefinition();
    }

    public static EntityDefinitionBuilder create() {
        return new EntityDefinitionBuilder();
    }

    public EntityDefinitionBuilder withExcelSheet(ExcelSheet excelSheet) {
        entityDefinition.setExcelSheet(excelSheet);
        return this;
    }

    public EntityDefinitionBuilder withExcelDataHeader(ExcelDataHeader excelDataHeader) {
        entityDefinition.setExcelDataHeader(excelDataHeader);
        return this;
    }

    public EntityDefinitionBuilder withFields(List<Field> fields) {
        entityDefinition.setFields(fields);
        return this;
    }

    public EntityDefinition build() {
        return entityDefinition;
    }
}
