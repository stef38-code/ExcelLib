package org.stephane.excel.tools;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.stephane.excel.annotations.ExcelCell;

@Slf4j
public class CellTools {

    public static final String ERROR_STRING_VALUE_OF_THE_CELL = "error decoding string value of the cell";

    private CellTools() {
        throw new UnsupportedOperationException("CellTools is a utility class and cannot be instantiated");
    }

    public static Object getValue(Row row, ExcelCell annotation, Cell cell) {
        return returnValue(row, annotation, cell);
    }

    private static Object returnValue(Row row, ExcelCell annotation, Cell cell) {
        log.debug("ObjectValue -> type de cellule {} ", cell.getCellType());
        switch (cell.getCellType()) {
            case NUMERIC:
                return CellNumeric.getNumericValue(annotation, cell);
            case STRING:
                return cell.getStringCellValue();
            case ERROR:
                return String.valueOf(cell.getErrorCellValue());
            case BLANK:
                return StringUtils.EMPTY;
            case FORMULA:
                return CellFormula.getFormulaValue(row, annotation, cell);
            case BOOLEAN:
                return CellBoolean.getBooleanValue(annotation, cell);
            default:
                return ERROR_STRING_VALUE_OF_THE_CELL;
        }
    }
}
