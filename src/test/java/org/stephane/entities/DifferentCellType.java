package org.stephane.entities;

import lombok.Getter;
import lombok.Setter;
import org.stephane.excel.annotations.ExcelCell;
import org.stephane.excel.annotations.ExcelDataHeader;
import org.stephane.excel.annotations.ExcelSheet;

import java.util.Date;

@ExcelSheet(name = "Feuil1")
@ExcelDataHeader(rowNumber = 0)
@Getter
@Setter
public class DifferentCellType {
    @ExcelCell(number =0)
    private Date date;
    @ExcelCell(number =1)
    private double numeric;
    @ExcelCell(number =2)
    private String string;
    @ExcelCell(number =3)
    private String error;
    @ExcelCell(number =4)
    private String blank;
    @ExcelCell(number =5)
    private String formula;
    @ExcelCell(number =6)
    private boolean aBoolean;
}
