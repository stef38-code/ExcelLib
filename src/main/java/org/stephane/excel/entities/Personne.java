package org.stephane.excel.entities;

import lombok.Getter;
import lombok.Setter;
import org.stephane.excel.annotations.ExcelCell;
import org.stephane.excel.annotations.ExcelDataHeader;
import org.stephane.excel.annotations.ExcelSheet;

@ExcelSheet
@ExcelDataHeader
@Getter
@Setter
public class Personne {
    @ExcelCell(number =0)
    private String name;
    @ExcelCell(number =1)
    private String company;
    @ExcelCell(number =2)
    private String address;
    @ExcelCell(number =3)
    private String postalZip;
    @ExcelCell(number =4)
    private String city;
    @ExcelCell(number =5)
    private String guid;
}
