package com.reflection;

import java.lang.reflect.Field;

public final class ClassChecker {

    public static Field[] getClassFields(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();

        if (fields == null) {
            throw new NullPointerException("There are no any fields  at the class");
        }
        return fields;
    }

}
