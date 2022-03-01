package org.stephane.excel.tools;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.stephane.excel.annotations.ExcelCell;

public class CellNumeric {

    private CellNumeric() {
        throw new UnsupportedOperationException("CellNumeric is a utility class and cannot be instantiated");
    }

    public static Object getNumericValue(ExcelCell annotation, Cell cell) {
        if (annotation.stringFormat()) {
            double numericCellValue = cell.getNumericCellValue();
            return String.valueOf(numericCellValue);
        }
        if (DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue();
        }
        return cell.getNumericCellValue();
    }
}
