package com.reflection.queryGenerator;

import com.reflection.annotation.Column;
import com.reflection.annotation.Table;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class QueryGeneratorTest {
    String getAll = "SELECT (id, firstName, salary) FROM TableName";
    String getById = "SELECT (id, firstName, salary) FROM TableName WHERE ID=0";
    String update = "UPDATE TableName SET id, firstName, salary";
    String insert = "INSERT INTO TableName (id, firstName, salary) VALUES (0, null, 0.0)";
    String delete = "DELETE (id, firstName, salary) FROM TableName WHERE ID=5";
    QueryGenerator queryGenerator = new QueryGenerator();

    @Table(name = "TableName")
    final static class Persons {

        @Column(name = "id")
        private int id;

        @Column(name = "firstName")
        private String name;

        @Column(name = "salary")
        private double salary;

    }

    @Test
    public void getAll() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        assertEquals(getAll, queryGenerator.getAll(Persons.class));
    }

    @Test
    public void insert() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        assertEquals(getById, queryGenerator.getById(Persons.class, Persons.class));
    }

    @Test
    public void update() throws ClassNotFoundException {
        assertEquals(update, queryGenerator.update(Persons.class));
    }

    @Test
    public void getById() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        assertEquals(insert, queryGenerator.insert(Persons.class));
    }

    @Test
    public void delete() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        assertEquals(delete, queryGenerator.delete(Persons.class, 5));
    }
}