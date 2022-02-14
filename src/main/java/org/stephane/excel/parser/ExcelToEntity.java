package org.stephane.excel.parser;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.stephane.excel.ExcelException;
import org.stephane.excel.ExcelFile;
import org.stephane.excel.annotations.ExcelCell;
import org.stephane.excel.annotations.ExcelDataHeader;
import org.stephane.excel.annotations.ExcelSheet;
import org.stephane.excel.tools.CellTools;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

@Slf4j
public class ExcelToEntity extends JavaReflection {


    private final AnalyseClass analyseClass = new AnalyseClass();
    private final ExcelFile excelFile = new ExcelFile();

    /**
     * conversion d'un onglet Excel en une liste de classe
     *
     * @param fExcel le fichier Excel
     * @param tclass la classe des données
     * @param <T>    type de la classe de données
     * @return une liste de classe
     * @throws ExcelException en cas d'une erreur
     */
    public <T> List<T> parse(String fExcel, Class<T> tclass) throws ExcelException {
        log.debug("//0. Analyse des annotations de la classe");
        EntityDefinition entityDefinition = analyseClass.check(tclass);

        if (!entityDefinition.isContainsFieldAnnotations()) {
            log.error("Annotation not found in class {}",tclass.getSimpleName());
            throw new ExcelException("Annotation not found !!");
        }
        log.debug("//1. open file selection sheet");
        Workbook workbook = excelFile.readXls(fExcel);
        ExcelSheet excelSheetAnnotation = entityDefinition.getExcelSheet();
        Sheet sheetSelected = getSheetSelected(excelSheetAnnotation, workbook);
        log.debug("//2. info sur le début ( ligne de définition )");
        ExcelDataHeader excelDataHeaderAnnotation = entityDefinition.getExcelDataHeader();
        int numberDataHeader = getNumberRowHeader(excelDataHeaderAnnotation);
        log.debug("//3. creation de les entities");
        List<T> listEntities = createListEntities(tclass, entityDefinition, sheetSelected, numberDataHeader);
        log.debug("//4. fermeture du workbook");
        closeWorkBook(workbook);
        return listEntities;

    }

    private void closeWorkBook(Workbook workbook) throws ExcelException {
        try {
            workbook.close();
        } catch (IOException e) {
            log.error("Erreur sur la fermeture", e);
            throw new ExcelException("Erreur fermeture");
        }
    }

    private <T> List<T> createListEntities(Class<T> tclass, EntityDefinition entityDefinition, Sheet sheetSelected, int numberDataHeader) throws ExcelException {
        List<Field> fields = entityDefinition.getFields();
        List<T> entities = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(fields)) {
            Iterator<Row> rowIterator = sheetSelected.rowIterator();
            readRowOneByOne(rowIterator, fields, entities, tclass, numberDataHeader);
        }
        return entities;
    }

    private <T> void readRowOneByOne(Iterator<Row> rowIterator, List<Field> fields, List<T> entities, Class<T> tclass, int numberDataHeader) throws ExcelException {
        if (!rowIterator.hasNext()) {
            return;
        }
        Row row = rowIterator.next();
        log.debug("current row: {}", row.getRowNum());
        //
        if (row.getRowNum() > numberDataHeader) {
            log.debug("add new Entity with number row {}", row.getRowNum());
            entities.add(createEntity(tclass, fields, row));
        }
        //la ligne suivante
        readRowOneByOne(rowIterator, fields, entities, tclass, numberDataHeader);
    }


    private <T> T createEntity(Class<T> tclass, List<Field> fields, Row row) throws ExcelException {
        T entity = getNewInstance(tclass);
        ListIterator<Field> fieldListIterator = fields.listIterator();
        readFieldOneByOne(fieldListIterator,row, entity);
        return entity;
    }

    private <T> void readFieldOneByOne(Iterator<Field> fieldIterator,Row row, T entity) throws ExcelException {
        if (!fieldIterator.hasNext()) {
            return;
        }
        Field field = fieldIterator.next();

        ExcelCell annotation = field.getAnnotation(ExcelCell.class);
        Cell cell = row.getCell(annotation.number());
        //todo faire la gestion des type de cellule
        //ici force le format string
        if (!Objects.isNull(cell)) {
            log.debug("field name: {} cellule: [{},{}] value {}", field.getName(), row.getRowNum(), annotation.number(), CellTools.returnStringValue(cell));
            setterField(entity, field.getName(), CellTools.getValue(annotation, cell));
        } else {
            log.warn("Cell NULL field name: {} cellule: [{},{}]", field.getName(), row.getRowNum(), annotation.number());
        }
        readFieldOneByOne( fieldIterator,row, entity);
    }
    private int getNumberRowHeader(ExcelDataHeader excelDataHeader) {
        if (Objects.isNull(excelDataHeader) )
            return 0;

        return excelDataHeader.rowNumber();
    }

    private Sheet getSheetSelected(ExcelSheet excelSheet, Workbook workbook) {
        if (Objects.isNull(excelSheet) || excelSheet.name().isBlank())
            return workbook.getSheetAt(0);

        return workbook.getSheet(excelSheet.name());
    }
}
