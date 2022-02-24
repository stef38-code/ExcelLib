package org.stephane.excel.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stephane.entities.DifferentCellType;
import org.stephane.entities.EvaluateFormulaType;
import org.stephane.excel.exceptions.ExcelException;
import org.stephane.excel.tools.FileUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class ParseDifferentCellTypeTest {
    private ExcelToEntity excelToEntity;
    private DifferentCellType differentCellType;
    @BeforeEach
    void setUp() {
        excelToEntity = new ExcelToEntity();
    }
    @Test
    void parse_fileExcelDifferentCellType_entityDifferentCellType() throws ExcelException, ParseException {
        String fExcel = FileUtil.getAbsolutePath("differentCellType.xls");
        List<DifferentCellType> differentCellTypes = excelToEntity.parse(fExcel, DifferentCellType.class);
        then(differentCellTypes).isNotNull().isNotEmpty().hasSize(1);
        DifferentCellType differentCellType = differentCellTypes.get(0);
        Date parse = new SimpleDateFormat("dd/MM/yyyy").parse("28/01/2222");
        then(differentCellType.getDate()).isEqualTo(parse);
        then(differentCellType.getNumeric()).isEqualTo(9999.99);
        then(differentCellType.getString()).isEqualTo("Hello Word");
        then(differentCellType.getError()).isEqualTo("\"lbonjour\"");
        then(differentCellType.getBlank()).isBlank();
        then(differentCellType.getFormula()).isEqualTo("CONCATENATE(C2,\" joe\")");
        then(differentCellType.isABoolean()).isTrue();

    }
    @Test
    void parse_fileExcelDifferentCellType_entityEvaluateFormulaType() throws ExcelException, ParseException {
        String fExcel = FileUtil.getAbsolutePath("differentCellType.xls");
        List<EvaluateFormulaType> evaluateFormulaTypes = excelToEntity.parse(fExcel, EvaluateFormulaType.class);
        then(evaluateFormulaTypes).isNotNull().isNotEmpty().hasSize(1);
        EvaluateFormulaType evaluateFormulaType = evaluateFormulaTypes.get(0);
        then(evaluateFormulaType.getFormula()).isEqualTo("CONCATENATE(C2,\" joe\")");
        then(evaluateFormulaType.getFormulaValue()).hasToString("Hello Word joe");

    }
}
