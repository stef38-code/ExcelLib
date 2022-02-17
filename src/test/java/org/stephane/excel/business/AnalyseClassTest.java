package org.stephane.excel.business;

import org.junit.jupiter.api.Test;
import org.stephane.excel.annotations.ExcelDataHeader;
import org.stephane.excel.annotations.ExcelSheet;
import org.stephane.entities.Personne;
import org.stephane.excel.parser.entities.EntityDefinition;

import static org.assertj.core.api.BDDAssertions.then;

class AnalyseClassTest {


    @Test
    void check_ClassPersonne_AllExcelAnnotation() {
        EntityDefinition entityDefinition = AnalyseClass.check(Personne.class);
        then(entityDefinition.getExcelSheet()).isNotNull();
        then(entityDefinition.getExcelDataHeader()).isNotNull();
        then(entityDefinition.getFields()).isNotNull();
    }

    @Test
    void check_ClassFirst_NotExcelAnnotation() {
        EntityDefinition entityDefinition = AnalyseClass.check(First.class);
        then(entityDefinition.getExcelSheet()).isNull();
        then(entityDefinition.getExcelDataHeader()).isNull();
        then(entityDefinition.getFields()).isNull();
    }

    @Test
    void check_ClassSecond() {
        EntityDefinition entityDefinition = AnalyseClass.check(Second.class);
        then(entityDefinition.getExcelSheet()).isNotNull();
        then(entityDefinition.getExcelDataHeader()).isNull();
        then(entityDefinition.getFields()).isNull();
    }

    @Test
    void check_ClassThird() {
        EntityDefinition entityDefinition = AnalyseClass.check(Third.class);
        then(entityDefinition.getExcelSheet()).isNotNull();
        then(entityDefinition.getExcelDataHeader()).isNotNull();
        then(entityDefinition.getFields()).isNull();
    }

    @Test
    void check_ClassFourth() {
        EntityDefinition entityDefinition = AnalyseClass.check(Fourth.class);
        then(entityDefinition.getExcelSheet()).isNull();
        then(entityDefinition.getExcelDataHeader()).isNotNull();
        then(entityDefinition.getFields()).isNull();
    }

    private static class First {

    }

    @ExcelSheet
    private static class Second {

    }

    @ExcelSheet
    @ExcelDataHeader
    private static class Third {

    }

    @ExcelDataHeader
    private static class Fourth {

    }

}
