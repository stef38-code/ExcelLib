package org.stephane.excel.parser;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stephane.entities.Chien;
import org.stephane.entities.Personne;
import org.stephane.excel.exceptions.ExcelException;
import org.stephane.excel.tools.FileUtil;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class ParseXlsFile {
    private ExcelToEntity excelToEntity;
    private String fExcel;
    @BeforeEach
    void setUp() {
        excelToEntity = new ExcelToEntity();
    }
    @Test
    void parse_fileExcel_entityPersonne() throws ExcelException {
        String fExcel = FileUtil.getAbsolutePath("exemple.xls");
        List<Personne> personnes = excelToEntity.parse(fExcel, Personne.class);
        then(personnes).isNotNull().isNotEmpty().hasSize(30);
        personnes.forEach(this::thenPersonneFieldNotNull);
        thenSpecificField(personnes.get(0), "Kelsie Knowles", "Ut Pellentesque Eget Inc.", "P.O. Box 206, 4536 In Road", "62860.0", "Saint-Louis", "1503737E-17EF-4B3E-3B9F-3B73863350DB");
        thenSpecificField(personnes.get(15), "Dane Kidd", "Molestie Company", "P.O. Box 208, 3812 Dolor, Rd.", "62824.0", "Limoges", "6EC185EB-3185-4C5E-413E-E45DDB4CA6FF");
        thenSpecificField(personnes.get(personnes.size() - 1), "Quynn Cox", "Bibendum Sed Associates", "Ap #868-4434 Eget Rd.", "21858.0", "Boulogne-sur-Mer", "CAE76A63-DA3C-09D7-7B11-C9DBA9FA310A");

    }
    @Test
    void parse_fileExcel_entityChien() throws ExcelException {
        fExcel = FileUtil.getAbsolutePath("dogregistration2.xls");
        List<Chien> chiens = excelToEntity.parse(fExcel, Chien.class);
        then(chiens).isNotNull().isNotEmpty().hasSize(1576);
        thenSpecificChien(chiens.get(0), "1772", "BRUCE", "RHODESIAN RIDGEBACK CROSS", "MALE", "LEFT COUNCIL AREA", "ADELAIDE");
        thenSpecificChien(chiens.get(961), "2269", "HUNTER", "BLOODHOUND", "MALE", "DECEASED", "NORTH ADELAIDE");
        thenSpecificChien(chiens.get(1575), "1521", "ELLA", "YORKSHIRE TERRIER CROSS", "FEMALE", "NORMAL MULTIPLE", "NORTH ADELAIDE");
    }

    private void thenPersonneFieldNotNull(Personne personne) {
        Assertions.assertThat(personne.getName()).isNotNull();
        Assertions.assertThat(personne.getPostalZip()).isNotNull();
        Assertions.assertThat(personne.getGuid()).isNotNull();
        Assertions.assertThat(personne.getAddress()).isNotNull();
        Assertions.assertThat(personne.getCompany()).isNotNull();
        Assertions.assertThat(personne.getCity()).isNotNull();
    }
    private void thenSpecificField(Personne personne, String name, String compagny, String address, String postalZip, String city, String guid) {
        then(personne.getName()).hasToString(name);
        then(personne.getPostalZip()).hasToString(postalZip);
        then(personne.getGuid()).hasToString(guid);
        then(personne.getAddress()).hasToString(address);
        then(personne.getCompany()).hasToString(compagny);
        then(personne.getCity()).hasToString(city);
    }
    private void thenSpecificChien(Chien chien, String reference, String nom, String race, String genre, String status, String localite) {
        then(chien.getRef()).hasToString(reference);
        then(chien.getNomAnimal()).hasToString(nom);
        then(chien.getRace()).hasToString(race);
        then(chien.getGenre()).hasToString(genre);
        then(chien.getStatut()).hasToString(status);
        then(chien.getLocalite()).hasToString(localite);
    }
}
