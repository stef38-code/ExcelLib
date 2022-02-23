package org.stephane.excel.parser;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.stephane.excel.annotations.ExcelDataHeader;
import org.stephane.excel.annotations.ExcelSheet;
import org.stephane.excel.business.AnalyseClass;
import org.stephane.excel.business.ExcelRow;
import org.stephane.excel.exceptions.ExcelException;
import org.stephane.excel.parser.entities.EntityDefinition;
import org.stephane.excel.tools.ExcelFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class ExcelToEntity extends ExcelRow {

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
        EntityDefinition entityDefinition = AnalyseClass.check(tclass);

        if (!entityDefinition.isContainsFieldAnnotations()) {
            log.error("Annotation not found in class {}", tclass.getSimpleName());
            throw new ExcelException("Annotation not found !!");
        }
        log.debug("//1. open file selection sheet");
        Workbook workbook = ExcelFile.readXls(fExcel);
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
            readRowOneByOne(sheetSelected.rowIterator(), fields, entities, tclass, numberDataHeader);
        }
        return entities;
    }


    private int getNumberRowHeader(ExcelDataHeader excelDataHeader) {
        if (Objects.isNull(excelDataHeader))
            return 0;

        return excelDataHeader.rowNumber();
    }

    private Sheet getSheetSelected(ExcelSheet excelSheet, Workbook workbook) throws ExcelException {
        if (Objects.isNull(excelSheet) || excelSheet.name().isBlank()) {
            return getSheetAt(workbook);
        }

        return getSheetWithName(excelSheet, workbook);
    }

    private Sheet getSheetAt(Workbook workbook) throws ExcelException {
        Sheet sheetAt = workbook.getSheetAt(0);
        if (Objects.isNull(sheetAt)) {
            throw new ExcelException("Excel Sheet not found !!");
        }
        return sheetAt;
    }

    private Sheet getSheetWithName (ExcelSheet excelSheet, Workbook workbook) throws ExcelException {
        Sheet sheet = workbook.getSheet(excelSheet.name());
        if (Objects.isNull(sheet)) {
            throw new ExcelException("Excel Sheet not found !!");
        }
        return sheet;
    }
}
