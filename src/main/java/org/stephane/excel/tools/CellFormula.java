package org.stephane.excel.tools;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.stephane.excel.annotations.ExcelCell;

@Slf4j
public class CellFormula {
    private CellFormula() {
        throw new AssertionError("classe utilitaire, constructeur interdit");
    }
    protected static Object getFormulaValue(Row row, ExcelCell annotation, Cell cell) {
        if (annotation.evaluateFormula()) {
            return CellFormula.evaluateformula(row, cell);
        }
        return cell.getCellFormula();
    }

    private static Object evaluateformula(Row row, Cell cell) {
        FormulaEvaluator evaluator = row.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
        CellType evaluateFormulaCell = evaluator.evaluateFormulaCell(cell);
        CellValue evaluateCell = evaluator.evaluate(cell);
        return CellFormula.returnValueWithFormula(evaluateFormulaCell, evaluateCell);
    }

    private static Object returnValueWithFormula(CellType cellType, CellValue valueCell) {
        log.debug("ObjectValue -> type de cellule {} ", cellType.name());
        switch (cellType) {
            case NUMERIC:
                return valueCell.getNumberValue();
            case STRING:
                return valueCell.getStringValue();
            case ERROR:
                return String.valueOf(valueCell.getErrorValue());
            case BLANK:
                return StringUtils.EMPTY;
            case BOOLEAN:
                return valueCell.getBooleanValue();
            default:
                return CellTools.ERROR_STRING_VALUE_OF_THE_CELL;
        }
    }
}
