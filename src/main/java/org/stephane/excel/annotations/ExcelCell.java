package org.stephane.excel.annotations;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelCell {
    int number() default 0;
    boolean stringFormat() default false;
}
