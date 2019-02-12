package com.reflection.queryGenerator;

import com.reflection.annotation.Column;
import com.reflection.annotation.Table;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueryGeneratorTest {
    private QueryGenerator queryGenerator = new QueryGenerator();

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
    public void getAll() throws IllegalAccessException, InstantiationException{
        assertEquals("SELECT (id, firstName, salary) FROM TableName", queryGenerator.getAll(Persons.class));
    }

    @Test
    public void insert() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        assertEquals("SELECT (id, firstName, salary) FROM TableName WHERE ID=0", queryGenerator.getById(Persons.class, Persons.class));
    }

    @Test
    public void update() throws ClassNotFoundException {
        assertEquals("UPDATE TableName SET id, firstName, salary", queryGenerator.update(Persons.class));
    }

    @Test
    public void getById() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        assertEquals("INSERT INTO TableName (id, firstName, salary) VALUES (0, null, 0.0)", queryGenerator.insert(Persons.class));
    }

    @Test
    public void delete() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        assertEquals("DELETE (id, firstName, salary) FROM TableName WHERE ID=5", queryGenerator.delete(Persons.class, 5));
    }
}