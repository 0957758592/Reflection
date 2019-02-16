package com.reflection.queryGenerator;

import com.reflection.annotation.Column;
import com.reflection.annotation.Table;

import java.lang.reflect.Field;

public class QueryGenerator {

    public String getAll(Class<?> clazz) {
        return "SELECT " + "(" + getFieldsName(clazz) + ") FROM " + getTableName(clazz);
    }

    public String insert(Object value) throws InstantiationException, IllegalAccessException {
        Class<?> clazz = value.getClass();
        return "INSERT INTO " + getTableName(clazz) + " (" + getFieldsName(clazz) + ")" + " VALUES " + "(" + getFieldsValue(value.getClass()) + ")";
    }

    public String update(Object value) {
        Class<?> clazz = value.getClass();
        return "UPDATE " + getTableName(clazz) + " SET " + getFieldsName(value.getClass());
    }

    public String getById(Class<?> clazz, Object id){
        return "SELECT " + "(" + getFieldsName(clazz) + ") FROM " + getTableName(clazz) + " WHERE " + getPrimaryName(clazz) + "=" + id;
    }

    public String delete(Class clazz, Object id){
        return "DELETE " + "(" + getFieldsName(clazz) + ") FROM " + getTableName(clazz) + " WHERE " + getPrimaryName(clazz) + "=" + id;
    }


    private String getColumnName(Field classField) {
        String columnName = classField.getAnnotation(Column.class).name();
        return columnName.equals("") ? classField.getName() : columnName;
    }


    private String getFieldsValue(Class classByObject) throws IllegalAccessException, InstantiationException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Field classField : classByObject.getDeclaredFields()) {
            if (classField.isAnnotationPresent(Column.class)) {
                classField.setAccessible(true);
                stringBuilder.append(classField.get(classByObject.newInstance())).append(", ");
                classField.setAccessible(false);
            }
        }
        return stringBuilder.substring(0, stringBuilder.lastIndexOf(","));
    }

    private String getFieldsName(Class<?> clazz) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Field classField : clazz.getDeclaredFields()) {
            if (classField.isAnnotationPresent(Column.class)) {
                stringBuilder.append(getColumnName(classField)).append(", ");
            }
        }

        return stringBuilder.substring(0, stringBuilder.lastIndexOf(","));
    }

    private String getPrimaryName(Class<?> clazz) {

        for (Field classField : clazz.getDeclaredFields()) {
            if (classField.isAnnotationPresent(Column.class)) {
                Column column = classField.getAnnotation(Column.class);
                if (column.primary()) {
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

}
