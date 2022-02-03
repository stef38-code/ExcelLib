package org.stephane.excel;

import org.stephane.excel.annotations.ExcelSheet;

@ExcelSheet()
public class Personne {
    private String name;
    private String company;
    private String address;
    private String postalZip;
    private String city;
    private String guid;
}
