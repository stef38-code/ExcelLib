package org.stephane.excel.annotations;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelSheet {
    int number() default 1;
    String name() default StringUtils.EMPTY;
}
