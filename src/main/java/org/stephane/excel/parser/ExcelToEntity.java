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

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
public class ExcelToEntity extends JavaReflection {


    private AnalyseClass analyseClass = new AnalyseClass();
    private ExcelFile excelFile = new ExcelFile();

    /**
     * conversion d'un onglet Excel en une liste de classe
     * @param fExcel le fichier Excel
     * @param tclass la classe des données
     * @param <T> type de la classe de données
     * @return une liste de classe
     * @throws ExcelException en cas d'une erreur
     */
    public <T> List<T> parse(String fExcel, Class<T> tclass) throws ExcelException {
        Workbook workbook = excelFile.readXls(fExcel);
        //0. Analyse des annotations de la classe
        EntityDefinition entityDefinition = analyseClass.check(tclass);
        //1. selection sheet
        Sheet sheetSelected = getSheetSelected(entityDefinition.getExcelSheet().orElse(null), workbook);
        //2. info sur le début ( ligne de définition )
        int numberDataHeader = getNumberRowHeader(entityDefinition.getExcelDataHeader());
        //3. creation de les entities
        return createListEntities(tclass, entityDefinition, sheetSelected, numberDataHeader);
    }

    private <T> List<T> createListEntities(Class<T> tclass, EntityDefinition entityDefinition, Sheet sheetSelected, int numberDataHeader) throws ExcelException {
        List<Field> fields = entityDefinition.getFields().orElse(Collections.emptyList());
        if (CollectionUtils.isNotEmpty(fields)) {
            List<T> entities = new ArrayList<>();

            for (Row row : sheetSelected) {
                log.info("numéro de ligne: {}", row.getRowNum());
                if (row.getRowNum() > numberDataHeader) {
                    log.info("numéro de ligne à traiter: {}", row.getRowNum());
                    entities.add(createEntity(tclass, fields, row));
                }
            }
            return entities;
        }
        return Collections.emptyList();
    }

    private <T> T createEntity(Class<T> tclass, List<Field> fields, Row row) throws ExcelException {
        T entity = getNewInstance(tclass);
        for (Field field : fields) {
            String fieldName = field.getName();
            ExcelCell annotation = field.getAnnotation(ExcelCell.class);
            Cell cell = row.getCell(annotation.number());
            //todo faire la gestion des type de cellule
            //ici force le format string
            if(! Objects.isNull(cell)) {
                log.info("field name: {} cellule: [{},{}] value {}", field.getName(), row.getRowNum(), annotation.number(), CellTools.returnStringValue(cell));
                setterField(entity, field.getName(), CellTools.getValue(annotation, cell));
            }else{
                log.warn("Cell NULL field name: {} cellule: [{},{}] value {}", field.getName(), row.getRowNum(), annotation.number());
            }
        }
        return entity;
    }

    private int getNumberRowHeader(Optional<ExcelDataHeader> excelDataHeader) {
        if (excelDataHeader.isPresent()) {
            return excelDataHeader.get().rowNumber();
        }
        return 0; //todo par défaut à mettre en variable static
    }

    private Sheet getSheetSelected(ExcelSheet excelSheet, Workbook workbook) {
        if (Objects.isNull(excelSheet))
            return workbook.getSheetAt(excelSheet.number());

        if (excelSheet.name().isBlank()) {
            return workbook.getSheetAt(excelSheet.number());
        }
        return workbook.getSheet(excelSheet.name());
    }
}
