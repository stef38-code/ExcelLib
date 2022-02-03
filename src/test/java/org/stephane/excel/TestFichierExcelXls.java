package org.stephane.excel;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stephane.excel.tools.FileUtil;

import java.io.IOException;

import static org.assertj.core.api.BDDAssertions.then;

class TestFichierExcelXls {
    String fExcel;
    private ExcelFile excelFile;

    @BeforeEach
    void setUp() {
        fExcel = FileUtil.getAbsolutePath("exemple.xls");
        excelFile = new ExcelFile();
    }

    @Test
    void readWorkBookXls_Lorsque_ouvertUnFichierExcelXls_Attend_workbookNonNull() throws IOException {
        //Conditions préalables (given)
        excelFile.readXls(fExcel);
        //Une action se produit (when)
        Workbook workbook = excelFile.getWorkbook();
        //Vérifier la sortie (then)
        then(workbook).isNotNull();
    }

    @Test
    void readWorkBookXls_Lorsque_UnFichierExcelAbsentXls_Attend_newWorkbook() throws IOException {
        //Conditions préalables (given)
        excelFile.readXls("fichierAbsent.xls");
        //Une action se produit (when)
        Workbook workbook = excelFile.getWorkbook();

        //Vérifier la sortie (then)
        then(workbook).isNotNull();
    }

}
