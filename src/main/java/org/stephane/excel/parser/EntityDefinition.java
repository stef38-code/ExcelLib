package org.stephane.excel.parser;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.stephane.excel.annotations.ExcelDataHeader;
import org.stephane.excel.annotations.ExcelSheet;

import java.lang.reflect.Field;
import java.util.List;

@Getter
@Setter
public class EntityDefinition {
    private ExcelSheet excelSheet;
    private ExcelDataHeader excelDataHeader;
    private List<Field> fields;

    public boolean isContainsFieldAnnotations() {
        return CollectionUtils.isNotEmpty(fields);
    }
}
