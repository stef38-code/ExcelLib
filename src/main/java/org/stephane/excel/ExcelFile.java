package org.stephane.excel;

import lombok.Getter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;

public class ExcelFile {
    private File excelFile;
    @Getter
    private Workbook workbook;

    public void readXls(String excelFile) throws IOException {
        this.excelFile = new File(excelFile);
        if (!this.excelFile.exists()) {
            workbook = WorkbookFactory.create(false);
        } else {
            workbook = WorkbookFactory.create(this.excelFile);
        }
    }

    public org.apache.poi.ss.usermodel.Workbook getWorkbook() {
        return this.workbook;
    }
}
