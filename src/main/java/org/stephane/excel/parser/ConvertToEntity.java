package org.stephane.excel.parser;

import org.apache.poi.ss.formula.functions.T;
import org.stephane.excel.entities.Personne;

import java.util.Collections;
import java.util.List;

public abstract class ConvertToEntity<T> {
    protected AnalyseClass analyseClass = new AnalyseClass();
    public List<T> parse(String fExcel, Class<T> personneClass) {

        return Collections.emptyList();
    }
}
