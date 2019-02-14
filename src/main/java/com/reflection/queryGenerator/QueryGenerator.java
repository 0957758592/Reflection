package com.reflection.queryGenerator;

import com.reflection.ClassChecker;
import com.reflection.annotation.Column;
import com.reflection.annotation.Table;

import java.lang.reflect.Field;

public class QueryGenerator {

    public String getAll(Class<?> clazz) {
        return "SELECT " + "(" + getFieldsName(clazz) + ") FROM " + getTableName(clazz);
    }

    public String insert(Object value) throws InstantiationException, IllegalAccessException {
        Class<?> clazz = value.getClass();
        return "INSERT INTO " + getTableName(clazz) + " (" + getFieldsName(clazz) + ")" + " VALUES " + "(" + getFieldsValue(clazz) + ")";
    }

    public String update(Object value) {
        Class<?> clazz = value.getClass();
        return "UPDATE " + getTableName(clazz) + " SET " + getFieldsName(clazz);
    }

    public String getById(Class<?> clazz, Object id) throws IllegalAccessException, InstantiationException {
        return "SELECT " + "(" + getFieldsName(clazz) + ") FROM " + getTableName(clazz) + " WHERE " + getPrimaryName(id.getClass()) + "=" + getId(id);
    }

    public String delete(Class clazz, Object id) throws IllegalAccessException, InstantiationException {
        return "DELETE " + "(" + getFieldsName(clazz) + ") FROM " + getTableName(clazz) + " WHERE " + getPrimaryName(id.getClass()) + "=" + getId(id);
    }


    private String getColumnName(Field classField) {
        String columnName = classField.getAnnotation(Column.class).name();
        return columnName.equals("") ? classField.getName() : columnName;
    }


    private String getId(Object id) throws InstantiationException, IllegalAccessException {
        if (id instanceof Integer) {
            return String.valueOf(id);
        }
        return getValueId(id);
    }

    private String getFieldsValue(Class classByObject) throws IllegalAccessException, InstantiationException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Field classField : ClassChecker.getClassFields(classByObject)) {
            if (classField.isAnnotationPresent(Column.class)) {
                classField.setAccessible(true);
                stringBuilder.append(classField.get(classByObject.newInstance())).append(", ");
                classField.setAccessible(false);
            } else {
                throw new NullPointerException("Table name can't be a null");
            }
        }
        return stringBuilder.substring(0, stringBuilder.lastIndexOf(","));
    }

    private String getFieldsName(Class<?> clazz) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Field classField : ClassChecker.getClassFields(clazz)) {
            if (classField.isAnnotationPresent(Column.class)) {
                classField.setAccessible(true);
                stringBuilder.append(getColumnName(classField)).append(", ");
                classField.setAccessible(false);
            } else {
                throw new NullPointerException("Column name can't be a null");
            }
        }

        return stringBuilder.substring(0, stringBuilder.lastIndexOf(","));
    }

    private String getPrimaryName(Class<?> clazz) {

        for (Field classField : ClassChecker.getClassFields(clazz)) {
            if (classField.isAnnotationPresent(Column.class)) {
                classField.setAccessible(true);
                Column column = classField.getAnnotation(Column.class);
                if (column.primary()) {
                    classField.setAccessible(true);
                    return column.name().equals("") ? classField.getName().toLowerCase() : column.name();
                }
            }
        }

        throw new RuntimeException("The primary name for " + clazz.getName() + " is not exist");
    }

    private String getTableName(Class<?> clazz) {

        if (clazz.isAnnotationPresent(Table.class)) {
            Table table = clazz.getAnnotation(Table.class);
            return table.name().equals("") ? clazz.getSimpleName().toLowerCase() : table.name();
        }

        throw new RuntimeException("The Object " + clazz.getName() + " can't be used as table, annotation @Table is not exist");
    }

    private String getValueId(Object classByObject) throws IllegalAccessException, InstantiationException {
        for (Field classField : ClassChecker.getClassFields(classByObject.getClass())) {
            if (classField.isAnnotationPresent(Column.class)) {
                if (classField.getName().equals("id")) {
                    classField.setAccessible(true);
                    return classField.get((classByObject.getClass()).newInstance()).toString();
                }
            }
        }
        throw new IllegalArgumentException("No any ID fields");
    }

}
