package org.stephane.excel.parser;

import lombok.Getter;
import lombok.Setter;
import org.stephane.excel.annotations.ExcelDataHeader;
import org.stephane.excel.annotations.ExcelSheet;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class EntityDefinition {
    private Optional<ExcelSheet> excelSheet = Optional.empty();
    private Optional<ExcelDataHeader> excelDataHeader= Optional.empty();
    private Optional<List<Field>> fields= Optional.empty();

    public boolean isContainsFieldAnnotations(){
        return getExcelDataHeader().isPresent();
    }
}
