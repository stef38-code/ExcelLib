package org.stephane.excel.parser;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.*;
import org.stephane.excel.ExcelException;
import org.stephane.excel.ExcelFile;
import org.stephane.excel.annotations.ExcelCell;
import org.stephane.excel.annotations.ExcelDataHeader;
import org.stephane.excel.annotations.ExcelSheet;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
public class ExcelToEntity extends JavaReflection {


    private AnalyseClass analyseClass = new AnalyseClass();
    private ExcelFile excelFile = new ExcelFile();

    public <T> List<T> parse(String fExcel, Class<T> tclass) throws ExcelException {

        List<T> entities = new ArrayList<>();
        Workbook workbook = excelFile.readXls(fExcel);

        EntityDefinition entityDefinition = analyseClass.check(tclass);

        //1. selection sheet
        Sheet sheetSelected = evaluateExcelSheet(entityDefinition.getExcelSheet().orElse(null), workbook);
        //entityDefinition.getExcelSheet().ifPresent();
        //2. info sur le début ( ligne de définition )
        int numberDataHeader = evaluateExcelDataHeader(entityDefinition.getExcelDataHeader());
        List<Field> fields = entityDefinition.getFields().orElse(Collections.emptyList());
        if (CollectionUtils.isNotEmpty(fields)) {


            for (Row row : sheetSelected) {
                log.info("numéro de ligne: {}", row.getRowNum());
                if (row.getRowNum() > numberDataHeader) {
                    log.info("numéro de ligne à traiter: {}", row.getRowNum());
                    T entity = getNewInstance(tclass);
                    fields.forEach(field -> {
                        String fieldName = field.getName();
                        ExcelCell annotation = field.getAnnotation(ExcelCell.class);
                        Cell cell = row.getCell(annotation.number());
                        //todo faire la gestion des type de cellule
                        //ici force le format string
                        log.info("field name: {} cellule: [{},{}] value {}", field.getName(), row.getRowNum(), annotation.number(), returnStringValue(cell));
                        try {
                            setterField(entity, field.getName(), returnStringValue(cell));
                        } catch (ExcelException e) {
                            log.error("Erreur affecttion", e);
                        }
                    });
                    entities.add(entity);
                }
            }
        }
        // return Collections.emptyList();
        return entities;
    }

    private int evaluateExcelDataHeader(Optional<ExcelDataHeader> excelDataHeader) {
        if (excelDataHeader.isPresent()) {
            return excelDataHeader.get().rowNumber();
        }
        return 0; //todo par défaut à mettre en variable static
    }

    private Sheet evaluateExcelSheet(ExcelSheet excelSheet, Workbook workbook) {
        if (Objects.isNull(excelSheet))
            return workbook.getSheetAt(excelSheet.number());

        if (excelSheet.name().isBlank()) {
            return workbook.getSheetAt(excelSheet.number());
        }
        return workbook.getSheet(excelSheet.name());
    }

    private String returnStringValue(Cell cell) {
        CellType cellType = cell.getCellType();

        switch (cellType) {
            case NUMERIC:
                double doubleVal = cell.getNumericCellValue();
                return String.valueOf(doubleVal);
            case STRING:
                return cell.getStringCellValue();
            case ERROR:
                return String.valueOf(cell.getErrorCellValue());
            case BLANK:
                return "";
            case FORMULA:
                return cell.getCellFormula();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
        }
        return "error decoding string value of the cell";
    }

}