package org.stephane.excel.parser;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.stephane.excel.ExcelException;
import org.stephane.excel.ExcelFile;
import org.stephane.excel.annotations.ExcelSheet;
import org.stephane.excel.entities.Personne;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

@Slf4j
public class ExcelToEntity extends JavaReflection {


    private AnalyseClass analyseClass = new AnalyseClass();
    private ExcelFile excelFile = new ExcelFile();

    public <T> List<T> parse(String fExcel, Class<T> tclass) throws ExcelException {
        T entity = getNewInstance(tclass);

        Workbook workbook = excelFile.readXls(fExcel);

        EntityDefinition entityDefinition = analyseClass.check(tclass);

        //selection sheet
        Sheet sheet;
        if(entityDefinition.getExcelSheet().isPresent()){
            ExcelSheet excelSheet = entityDefinition.getExcelSheet().get();
            sheet = evaluateExcelSheet(excelSheet,workbook);
        }else{
            sheet = workbook.getSheetAt(0);
        }


        //entityDefinition.getExcelSheet().ifPresent();

        return Collections.emptyList();
    }

    private Sheet evaluateExcelSheet(ExcelSheet excelSheet, Workbook workbook) {
        if(excelSheet.name().isBlank()){
            workbook.getSheetAt(excelSheet.number());
        }
        return workbook.getSheet(excelSheet.name());
    }


}
