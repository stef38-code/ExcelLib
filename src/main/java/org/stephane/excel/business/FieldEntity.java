package org.stephane.excel.business;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.stephane.excel.annotations.ExcelCell;
import org.stephane.excel.exceptions.ExcelException;
import org.stephane.excel.tools.CellTools;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Objects;

@Slf4j
public class FieldEntity extends JavaReflection {
    protected <T> void readFieldOneByOne(Iterator<Field> fieldIterator, Row row, T entity) throws ExcelException {
        if (!fieldIterator.hasNext()) {
            return;
        }
        analyseField(row, entity, fieldIterator.next());
        readFieldOneByOne(fieldIterator, row, entity);
    }

    private <T> void analyseField(Row row, T entity, Field field) throws ExcelException {
        setterFieldEntity(row, entity, field, field.getAnnotation(ExcelCell.class).number(), row.getCell(field.getAnnotation(ExcelCell.class).number()));
    }

    private <T> void setterFieldEntity(Row row, T entity, Field field, int number, Cell cell) throws ExcelException {
        if (!Objects.isNull(cell)) {
            log.debug("field name: {} cellule: [{},{}] value {}", field.getName(), row.getRowNum(), number, CellTools.returnStringValue(cell));
            setterField(entity, field.getName(), CellTools.getValue(field.getAnnotation(ExcelCell.class), cell));
        } else {
            log.warn("Cell NULL field name: {} cellule: [{},{}]", field.getName(), row.getRowNum(), number);
        }
    }
}
