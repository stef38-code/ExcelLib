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
        then(entityDefinition.getExcelSheet()).isNotNull();
        then(entityDefinition.getExcelDataHeader()).isNotNull();
        then(entityDefinition.getFields()).isNotNull();
    }
    @Test
    void check_ClassFirst_NotExcelAnnotation(){
        analyseClass.check(First.class);
        EntityDefinition entityDefinition = analyseClass.getEntityDefinition();
        then(entityDefinition.getExcelSheet()).isNull();
        then(entityDefinition.getExcelDataHeader()).isNull();
        then(entityDefinition.getFields()).isNull();
    }
    @Test
    void check_ClassSecond(){
        analyseClass.check(Second.class);
        EntityDefinition entityDefinition = analyseClass.getEntityDefinition();
        then(entityDefinition.getExcelSheet()).isNotNull();
        then(entityDefinition.getExcelDataHeader()).isNull();
        then(entityDefinition.getFields()).isNull();
    }
    @Test
    void check_ClassThird(){
        analyseClass.check(Third.class);
        EntityDefinition entityDefinition = analyseClass.getEntityDefinition();
        then(entityDefinition.getExcelSheet()).isNotNull();
        then(entityDefinition.getExcelDataHeader()).isNotNull();
        then(entityDefinition.getFields()).isNull();
    }
    @Test
    void check_ClassFourth(){
        analyseClass.check(Fourth.class);
        EntityDefinition entityDefinition = analyseClass.getEntityDefinition();
        then(entityDefinition.getExcelSheet()).isNull();
        then(entityDefinition.getExcelDataHeader()).isNotNull();
        then(entityDefinition.getFields()).isNull();
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
