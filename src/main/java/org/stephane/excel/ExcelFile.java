package org.stephane.excel;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;

@Slf4j
public class ExcelFile {
    private ExcelFile() {
    }

    @Getter
    private static Workbook workbook;

    public static Workbook readXls(String excelFile) throws ExcelException {
        try {
            File excelFile1 = new File(excelFile);
            if (!excelFile1.exists()) {

                workbook = WorkbookFactory.create(false);

            } else {
                workbook = WorkbookFactory.create(excelFile1);
            }
        } catch (IOException e) {
            log.error("Erreur lecture du fichier", e);
            throw new ExcelException("Erreur lecture du fichier:" + excelFile);
        }
        return workbook;
    }
}
