package org.stephane.excel.tools;

import org.apache.poi.ss.usermodel.Cell;
import org.stephane.excel.annotations.ExcelCell;

public class CellBoolean {
    private CellBoolean() {
    }

    protected static Object getBooleanValue(ExcelCell annotation, Cell cell) {
        if (annotation.stringFormat()) {
            return String.valueOf(cell.getBooleanCellValue());
        }
        return cell.getBooleanCellValue();
    }
}
