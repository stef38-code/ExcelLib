package org.stephane.excel.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stephane.excel.annotations.ExcelDataHeader;
import org.stephane.excel.annotations.ExcelSheet;
import org.stephane.excel.entities.Personne;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

class AnalyseClassTest {
    private AnalyseClass analyseClass;
    @BeforeEach
    void setUp() {
        analyseClass = new AnalyseClass();
    }
    @Test
    void check_ClassPersonne_AllExcelAnnotation(){
        analyseClass.check(Personne.class);
        EntityDefinition entityDefinition = analyseClass.getEntityDefinition();
        then(entityDefinition.getExcelSheet()).isPresent();
        then(entityDefinition.getExcelDataHeader()).isPresent();
        then(entityDefinition.getFields()).isPresent();
    }
    @Test
    void check_ClassFirst_NotExcelAnnotation(){
        analyseClass.check(First.class);
        EntityDefinition entityDefinition = analyseClass.getEntityDefinition();
        then(entityDefinition.getExcelSheet()).isNotPresent();
        then(entityDefinition.getExcelDataHeader()).isNotPresent();
        then(entityDefinition.getFields()).isNotPresent();
    }
    @Test
    void check_ClassSecond(){
        analyseClass.check(Second.class);
        EntityDefinition entityDefinition = analyseClass.getEntityDefinition();
        then(entityDefinition.getExcelSheet()).isPresent();
        then(entityDefinition.getExcelDataHeader()).isNotPresent();
        then(entityDefinition.getFields()).isNotPresent();
    }
    @Test
    void check_ClassThird(){
        analyseClass.check(Third.class);
        EntityDefinition entityDefinition = analyseClass.getEntityDefinition();
        then(entityDefinition.getExcelSheet()).isPresent();
        then(entityDefinition.getExcelDataHeader()).isPresent();
        then(entityDefinition.getFields()).isNotPresent();
    }
    @Test
    void check_ClassFourth(){
        analyseClass.check(Fourth.class);
        EntityDefinition entityDefinition = analyseClass.getEntityDefinition();
        then(entityDefinition.getExcelSheet()).isNotPresent();
        then(entityDefinition.getExcelDataHeader()).isPresent();
        then(entityDefinition.getFields()).isNotPresent();
    }
    private static class First{

    }
    @ExcelSheet
    private static class Second{

    }
    @ExcelSheet
    @ExcelDataHeader
    private static class Third {

    }

    @ExcelDataHeader
    private static class Fourth {

    }

}
