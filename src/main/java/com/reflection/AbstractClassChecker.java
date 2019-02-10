package com.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class AbstractClassChecker {

    protected void isClassExist(String name) {
        try {
            Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Field[] getClassFields(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();

        if (fields == null) {
            throw new NullPointerException("There are no any fields  at the class");
        }
        return fields;
    }

}
