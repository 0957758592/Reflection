package com.reflection.queryGenerator;

import com.reflection.AbstractClassChecker;
import com.reflection.annotation.Column;
import com.reflection.annotation.Table;

import java.lang.reflect.Field;
import java.util.Arrays;

public class QueryGenerator extends AbstractClassChecker {

    public String getAll(Class<?> clazz) throws InstantiationException, IllegalAccessException {
        return "SELECT " + "(" + getFieldsName(clazz) + ") FROM " + getTableName(clazz);
    }

    public String insert(Object value) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return "INSERT INTO " + getTableNameByObject(value) + " (" + getColumns(value) + ")" + " VALUES " + "(" + getValuesByObject(value) + ")";
    }

    public String update(Object value) throws ClassNotFoundException {
        return "UPDATE " + getTableNameByObject(value) + " SET " + getColumns(value);
    }

    public String getById(Class<?> clazz, Object id) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return "SELECT " + "(" + getFieldsName(clazz) + ") FROM " + getTableName(clazz) + " WHERE ID=" + getId(id);
    }

    private Integer getId(Object id) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (id instanceof Integer) {
            return (Integer) id;
        }
        return getValueObjectId(id);
    }


    private Integer getValueObjectId(Object id) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return getValueId(getClassByObject(id));

    }


    public String delete(Class clazz, Object id) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return "DELETE " + "(" + getFieldsName(clazz) + ") FROM " + getTableName(clazz) + " WHERE ID=" + getId(id);
    }


    private String getColumnName(Field classField) {
        String columnName = classField.getAnnotation(Column.class).name();
        return !columnName.equals("") ? columnName : classField.getName();
    }

    private String getColumns(Object value) throws ClassNotFoundException {
        return getFieldsName((Class<?>) getClassByObject(value));
    }

    private String getTableNameByObject(Object value) throws ClassNotFoundException {
        return getTableName((Class<?>) getClassByObject(value));
    }


    private String getValuesByObject(Object value) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return getObjectValue((Class) getClassByObject(value));
    }

    private String getObjectValue(Class classByObject) throws InstantiationException, IllegalAccessException {
        return getFieldsValue(classByObject);
    }

    private String getFieldsValue(Class classByObject) throws IllegalAccessException, InstantiationException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Field classField : getClassFields(classByObject)) {
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

        for (Field classField : getClassFields(clazz)) {
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

    private Object getClassByObject(Object value) throws ClassNotFoundException {
        if (!(value instanceof Class)) {
            if (value instanceof String) {
                value = Class.forName(value.toString());
            } else {
                value = value.getClass();
            }
        }
        return value;
    }


    private Integer getValueId(Object classByObject) throws IllegalAccessException, InstantiationException {
        for (Field classField : getClassFields((Class) classByObject)) {
            if (classField.isAnnotationPresent(Column.class)) {
                if (classField.getName().equals("id")) {
                    classField.setAccessible(true);
                    return (Integer) classField.get(((Class) classByObject).newInstance());
                }
            }
        }
        throw new IllegalArgumentException("No any ID fields");
    }

    private String getTableName(Class<?> clazz) {
        isClassExist(clazz.getName());

        if (clazz.isAnnotationPresent(Table.class)) {
            Table table = clazz.getAnnotation(Table.class);
            return !table.name().equals("") ? table.name() : clazz.getSimpleName().toLowerCase();
        }

        throw new NullPointerException("Table name can't be a null");
    }

}
