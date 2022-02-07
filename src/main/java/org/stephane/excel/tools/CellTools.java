package org.stephane.excel.tools;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.stephane.excel.annotations.ExcelCell;

public class CellTools {
    private CellTools() {
    }

    public static Object getValue(ExcelCell annotation, Cell cell) {
        if (annotation.stringFormat()) {
            return returnStringValue(cell);
        }
        return returnValue(cell);
    }

    private static Object returnValue(Cell cell) {
        CellType cellType = cell.getCellType();

        switch (cellType) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case STRING:
                return cell.getStringCellValue();
            case ERROR:
                return String.valueOf(cell.getErrorCellValue());
            case BLANK:
                return "";
            case FORMULA:
                return cell.getCellFormula();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                return "error decoding string value of the cell";
        }
    }

    public static String returnStringValue(Cell cell) {
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
                return "";
            case FORMULA:
                return cell.getCellFormula();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "error decoding string value of the cell";
        }
    }
}
