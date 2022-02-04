package org.stephane.excel;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;

@Slf4j
public class ExcelFile {
    private File excelFile;
    @Getter
    private Workbook workbook;

    public Workbook readXls(String excelFile) throws ExcelException {
        try {
            this.excelFile = new File(excelFile);
            if (!this.excelFile.exists()) {

                workbook = WorkbookFactory.create(false);

            } else {
                workbook = WorkbookFactory.create(this.excelFile);
            }
        } catch (IOException e) {
            log.error("Erreur lecture du fichier", e);
            throw new ExcelException("Erreur lecture du fichier:" + excelFile);
        }
        return workbook;
    }

    public org.apache.poi.ss.usermodel.Workbook getWorkbook() {
        return this.workbook;
    }
}
