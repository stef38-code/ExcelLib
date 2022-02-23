package org.stephane.entities;

import lombok.Getter;
import lombok.Setter;
import org.stephane.excel.annotations.ExcelCell;
import org.stephane.excel.annotations.ExcelDataHeader;
import org.stephane.excel.annotations.ExcelSheet;

@ExcelSheet
@ExcelDataHeader(rowNumber = 0)
@Getter
@Setter
public class Chien {
    // Reference
    @ExcelCell
    private String ref;
    // Animal_Name
    @ExcelCell(number =1)
    private String nomAnimal;
    // Breed_Description
    @ExcelCell(number =2)
    private String race;
    // Gender
    @ExcelCell(number =3)
    private String genre;
    // Status_Description
    @ExcelCell(number =4)
    private String statut;
    // Suburb
    @ExcelCell(number =5)
    private String localite;
}
