package org.stephane.excel;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stephane.excel.tools.FileUtil;

import static org.assertj.core.api.BDDAssertions.then;

class TestFichierExcelXls {
    String fExcel;

    @BeforeEach
    void setUp() {
        fExcel = FileUtil.getAbsolutePath("exemple.xls");
    }

    @Test
    void readWorkBookXls_Lorsque_ouvertUnFichierExcelXls_Attend_workbookNonNull() throws ExcelException {
        //Conditions préalables (given)
        Workbook workbook = ExcelFile.readXls(fExcel);
        //Une action se produit (when)
        //Vérifier la sortie (then)
        then(workbook).isNotNull();
    }

    @Test
    void readWorkBookXls_Lorsque_UnFichierExcelAbsentXls_Attend_newWorkbook() throws ExcelException {
        //Conditions préalables (given)
        Workbook workbook = ExcelFile.readXls("fichierAbsent.xls");
        //Une action se produit (when)

        //Vérifier la sortie (then)
        then(workbook).isNotNull();
    }

}
