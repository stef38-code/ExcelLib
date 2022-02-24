package org.stephane.excel.tools;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.stephane.excel.annotations.ExcelCell;

@Slf4j
public class CellTools {

    public static final String ERROR_STRING_VALUE_OF_THE_CELL = "error decoding string value of the cell";

    private CellTools() {
    }

    public static Object getValue(Row row,ExcelCell annotation, Cell cell) {
        return returnValue(row,annotation,cell);
    }

    private static Object returnValue(Row row,ExcelCell annotation,Cell cell) {
        log.debug("ObjectValue -> type de cellule {} ",cell.getCellType());
        CellType cellType = cell.getCellType();
        Object value;
        switch (cellType) {
            case NUMERIC:
                if (annotation.stringFormat()){
                    value = cell.getNumericCellValue();
                }
                if (DateUtil.isCellDateFormatted(cell)) {
                    value = cell.getDateCellValue();
                }
                value = cell.getNumericCellValue();
                break;
            case STRING:
                value = cell.getStringCellValue();
                break;
            case ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            case BLANK:
                value = StringUtils.EMPTY;
                break;
            case FORMULA:
                if(annotation.evaluateFormula()){
                    value = evaluateformula(row, cell);
                }
                value = cell.getCellFormula();
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            default:
                value = ERROR_STRING_VALUE_OF_THE_CELL;
                break;
        }
        if (annotation.stringFormat())
            return String.valueOf(cell.getBooleanCellValue());
        return value;
    }

    private static Object evaluateformula(Row row, Cell cell) {
        FormulaEvaluator evaluator = row.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
        CellType evaluateFormulaCell = evaluator.evaluateFormulaCell(cell);
        CellValue evaluateCell = evaluator.evaluate(cell);
        return returnValueWithFormula(evaluateFormulaCell, evaluateCell);
    }

    private static Object returnValueWithFormula(CellType cellType, CellValue valueCell) {
        log.debug("ObjectValue -> type de cellule {} ",cellType.name());
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
                return ERROR_STRING_VALUE_OF_THE_CELL;
        }
    }

   /* public static String returnStringValue(Cell cell) {
        log.debug("stringValue -> type de cellule {} value {}", cell.getCellType(), cell);
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
                return StringUtils.EMPTY;
            case FORMULA:
                return cell.getCellFormula();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return ERROR_STRING_VALUE_OF_THE_CELL;
        }
    }*/
}
