package org.stephane.excel.business;

import lombok.extern.slf4j.Slf4j;
import org.stephane.excel.exceptions.ExcelException;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

@Slf4j
public class JavaReflection {
    public <T> T getNewInstance(Class<T> tclass) throws ExcelException {
        try {
            return tclass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("Erreur creation nouvelle instance", e);
            throw new ExcelException("Erreur creation nouvelle instance:" + tclass.getSimpleName());
        }
    }

    public <T> void setterField(T tclass, String nameField, Object value) throws ExcelException {
        PropertyDescriptor pd = null;
        try {
            pd = new PropertyDescriptor(nameField, tclass.getClass());
            pd.getWriteMethod().invoke(tclass, value);
        } catch (IllegalArgumentException | InvocationTargetException | IntrospectionException | IllegalAccessException e) {
            log.error("Erreur setter", e);
            throw new ExcelException(tclass.getClass(), nameField, getTypeValue(pd), value);
        }
    }

    private String getTypeValue(PropertyDescriptor pd) {
        if (Objects.isNull(pd)) {
            return "!! UNDEFINE !!";
        }
        return pd.getReadMethod().getReturnType().getSimpleName();
    }
}
