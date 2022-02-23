package org.stephane.excel.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stephane.entities.DifferentCellType;
import org.stephane.entities.Personne;
import org.stephane.excel.exceptions.ExcelException;
import org.stephane.excel.tools.FileUtil;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class ParseDifferentCellType {
    private ExcelToEntity excelToEntity;
    private DifferentCellType differentCellType;
    @BeforeEach
    void setUp() {
        excelToEntity = new ExcelToEntity();
    }
    @Test
    void parse_fileExcelDifferentCellType_entityDifferentCellType() throws ExcelException {
        String fExcel = FileUtil.getAbsolutePath("differentCellType.xls");
        List<DifferentCellType> differentCellTypes = excelToEntity.parse(fExcel, DifferentCellType.class);
        then(differentCellTypes).isNotNull().isNotEmpty().hasSize(1);
    }
}
