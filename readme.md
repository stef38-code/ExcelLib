# ExcelLib
Cette librairie permet la conversion d'un onglet(sheet) Excel en une classe.
Grâce à des simples annotations.

# Les Annotations

- **@ExcelSheet**
  - name : nom de l'onglet sur lequel on veut travailler par défault "Feuil1".
- **@ExcelDataHeader**
  - rowNumber : la position de la ligne qui contient les entêtes par défaut 1.
- **@ExcelCell**
  - number : le numéro de la cellule par défaut 0.
  - stringFormat : permet de convertir en un format String une cellule qui ne l'ai pas.
  
Exemple:
```java 
@ExcelSheet(name = "Feuil1")
@ExcelDataHeader(rowNumber = 0)
@Getter
@Setter
public class Personne {
    @ExcelCell
    private String name;
    @ExcelCell(number =1)
    private String company;
    @ExcelCell(number =2)
    private String address;
    @ExcelCell(number =3, stringFormat = true)
    private String postalZip;
    @ExcelCell(number =4)
    private String city;
    @ExcelCell(number =5)
    private String guid;
}
```

`ATTENTION: les setter sont obligatoires`

# Conversion

```java
import org.stephane.entities.Personne;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stephane.entities.Personne;
import org.stephane.excel.exceptions.ExcelException;
import org.stephane.excel.parser.ExcelToEntity;
import org.stephane.excel.tools.FileUtil;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class ExcelToEntityTest {
    String fExcel;

    @BeforeEach
    void setUp() {
        fExcel = FileUtil.getAbsolutePath("exemple.xls");
    }

    @Test
    void parse_fileExcel_entityPersonne() throws ExcelException {
        ExcelToEntity excelToEntity = new ExcelToEntity();
        List<Personne> personnes = excelToEntity.parse(fExcel, Personne.class);
        then(personnes).isNotNull().isNotEmpty().hasSize(30);
        personnes.forEach(this::thenFieldNotNull);
        thenSpecificField(personnes.get(0), "Kelsie Knowles", "Ut Pellentesque Eget Inc.", "P.O. Box 206, 4536 In Road", "62860.0", "Saint-Louis", "1503737E-17EF-4B3E-3B9F-3B73863350DB");
        thenSpecificField(personnes.get(15), "Dane Kidd", "Molestie Company", "P.O. Box 208, 3812 Dolor, Rd.", "62824.0", "Limoges", "6EC185EB-3185-4C5E-413E-E45DDB4CA6FF");
        thenSpecificField(personnes.get(personnes.size() - 1), "Quynn Cox", "Bibendum Sed Associates", "Ap #868-4434 Eget Rd.", "21858.0", "Boulogne-sur-Mer", "CAE76A63-DA3C-09D7-7B11-C9DBA9FA310A");

    }

    private void thenSpecificField(Personne personne, String name, String compagny, String address, String postalZip, String city, String guid) {
        then(personne.getName()).hasToString(name);
        then(personne.getPostalZip()).hasToString(postalZip);
        then(personne.getGuid()).hasToString(guid);
        then(personne.getAddress()).hasToString(address);
        then(personne.getCompany()).hasToString(compagny);
        then(personne.getCity()).hasToString(city);
    }

    private void thenFieldNotNull(Personne personne) {
        then(personne.getName()).isNotNull();
        then(personne.getPostalZip()).isNotNull();
        then(personne.getGuid()).isNotNull();
        then(personne.getAddress()).isNotNull();
        then(personne.getCompany()).isNotNull();
        then(personne.getCity()).isNotNull();
    }

}
```

# Sources
Les sources qui m'ont aidées

[Create XLSX Parser with custom annotations using Apache POI Library](https://frontbackend.com/java/create-xlsx-parser-with-custom-annotations-using-apache-poi-library)

[A Generic way to write Excel files using Apache POI and Java Reflection](https://medium.com/javarevisited/a-generic-approach-to-write-excel-using-apache-poi-17a1dfd4b98e)
