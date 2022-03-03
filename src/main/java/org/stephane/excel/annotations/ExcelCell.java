package org.stephane.excel.annotations;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelCell {
    int number() default 0;
    String columnName() default StringUtils.EMPTY;
    boolean stringFormat() default false;
    boolean evaluateFormula() default false;
}
