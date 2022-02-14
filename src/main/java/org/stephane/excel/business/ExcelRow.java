package org.stephane.excel.business;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.stephane.excel.ExcelException;
import org.stephane.excel.business.FieldEntity;
import org.stephane.excel.parser.ExcelToEntity;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class ExcelRow extends FieldEntity {
    private <T> T createEntity(Class<T> tclass, List<Field> fields, Row row) throws ExcelException {
        T entity = getNewInstance(tclass);
        readFieldOneByOne(fields.listIterator(), row, entity);
        return entity;
    }

    protected <T> void readRowOneByOne(Iterator<Row> rowIterator, List<Field> fields, List<T> entities, Class<T> tclass, int numberDataHeader) throws ExcelException {
        if (!rowIterator.hasNext()) {
            return;
        }
        analyseRow(rowIterator, fields, entities, tclass, numberDataHeader);
        //la ligne suivante
        readRowOneByOne(rowIterator, fields, entities, tclass, numberDataHeader);
    }

    private <T> void analyseRow(Iterator<Row> rowIterator, List<Field> fields, List<T> entities, Class<T> tclass, int numberDataHeader) throws ExcelException {
        addEntityWithRow(fields, entities, tclass, numberDataHeader, rowIterator.next());
    }

    private <T> void addEntityWithRow(List<Field> fields, List<T> entities, Class<T> tclass, int numberDataHeader, Row row) throws ExcelException {
        log.debug("current row: {}", row.getRowNum());
        //
        if (row.getRowNum() > numberDataHeader) {
            log.debug("add new Entity with number row {}", row.getRowNum());
            entities.add(createEntity(tclass, fields, row));
        }
    }
}
