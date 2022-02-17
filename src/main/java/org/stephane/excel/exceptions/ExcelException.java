package org.stephane.excel.exceptions;

import java.text.MessageFormat;

public class ExcelException extends Exception {
    private static final String SETTER_MSG = "Erreur Setter class<{0}> field<{1}({2})> value<{3}({4})>";
    private final String  message;

    public ExcelException(String message) {
        this.message = message;
    }

    public ExcelException(Class<?> tclass, String nameField, String typeValue, Object value) {
        this.message = MessageFormat.format(SETTER_MSG, tclass.getSimpleName(), nameField,typeValue, value,value.getClass().getSimpleName());
    }

    @Override
    public String getMessage() {
        return message;
    }
}
