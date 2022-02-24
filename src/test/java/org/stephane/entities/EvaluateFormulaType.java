package org.stephane.entities;

import lombok.Getter;
import lombok.Setter;
import org.stephane.excel.annotations.ExcelCell;
import org.stephane.excel.annotations.ExcelDataHeader;
import org.stephane.excel.annotations.ExcelSheet;

@ExcelSheet(name = "Feuille1")
@ExcelDataHeader(rowNumber = 0)
@Getter
@Setter
public class EvaluateFormulaType {
    @ExcelCell(number =4)
    private String formula;
    @ExcelCell(number =4, evaluateFormula = true)
    private String formulaValue;
}
