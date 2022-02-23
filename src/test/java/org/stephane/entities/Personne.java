package org.stephane.entities;

import lombok.Getter;
import lombok.Setter;
import org.stephane.excel.annotations.ExcelCell;
import org.stephane.excel.annotations.ExcelDataHeader;
import org.stephane.excel.annotations.ExcelSheet;

@ExcelSheet(name = "Feuil1")
@ExcelDataHeader(rowNumber = 0)
@Getter
@Setter
public class Personne {
    @ExcelCell
    private String name;
    @ExcelCell(number = 1)
    private String company;
    @ExcelCell(number = 2)
    private String address;
    @ExcelCell(number = 3, stringFormat = true)
    private String postalZip;
    @ExcelCell(number = 4)
    private String city;
    @ExcelCell(number = 5)
    private String guid;
}
