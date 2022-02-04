package org.stephane.excel.annotations;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelDataHeader {
    int rowNumber() default 1;
}
