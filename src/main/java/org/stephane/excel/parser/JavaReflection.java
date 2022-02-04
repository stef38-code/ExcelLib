package org.stephane.excel.parser;

import lombok.extern.slf4j.Slf4j;
import org.stephane.excel.ExcelException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Slf4j
public class JavaReflection {
    protected  <T> T getNewInstance(Class<T> tclass) throws ExcelException {
        try {
            return tclass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("Erreur creation nouvelle instance",e);
            throw new ExcelException("Erreur creation nouvelle instance:"+tclass.getSimpleName());
        }
    }

    protected <T> void setterField(T tclass, String nameField, String value) throws ExcelException {
        Field field = null;
        try {
            field = tclass.getClass().getDeclaredField(nameField);
            field.setAccessible(true);
            field.set(tclass, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("Erreur setter",e);
            throw new ExcelException("Erreur setter :"+tclass.getClass().getSimpleName());
        }
    }
}
