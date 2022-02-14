package org.stephane.excel.annotations.business;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnnotationInClass {
    private AnnotationInClass() {
    }

    protected static <T, A extends Annotation> boolean isAnnotationPresent(Class<T> tClass, Class<A> aClass) {
        return tClass.isAnnotationPresent(aClass);
    }

    protected static <T, A extends Annotation> boolean isFieldAnnotationPresent(Class<T> tClass, Class<A> aClass) {
        return Stream.of(tClass.getDeclaredFields())
                .anyMatch(field -> Objects.nonNull(field.getAnnotation(aClass)));
    }

    protected static <T, A extends Annotation> A getClassAnnotation(Class<T> tClass, Class<A> aClass) {
        return tClass.getAnnotation(aClass);
    }

    protected static <T, A extends Annotation> List<Field> getFieldContainAnnotation(Class<T> tClass, Class<A> aClass) {
        return Stream.of(tClass.getDeclaredFields())
                .filter(field -> Objects.nonNull(field.getAnnotation(aClass)))
                .collect(Collectors.toList());
    }
}
