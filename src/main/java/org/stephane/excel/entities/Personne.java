package org.stephane.excel.entities;

import org.stephane.excel.annotations.ExcelCell;
import org.stephane.excel.annotations.ExcelDataHeader;
import org.stephane.excel.annotations.ExcelSheet;

@ExcelSheet
@ExcelDataHeader
public class Personne {
    @ExcelCell(number =1)
    private String name;
    @ExcelCell(number =2)
    private String company;
    @ExcelCell(number =3)
    private String address;
    @ExcelCell(number =4)
    private String postalZip;
    @ExcelCell(number =5)
    private String city;
    @ExcelCell(number =6)
    private String guid;
}
