package com.reflection.queryGenerator;

import com.reflection.annotation.Column;
import com.reflection.annotation.Table;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueryGeneratorTest {
    private QueryGenerator queryGenerator = new QueryGenerator();

    @Table(name = "TableName")
    final static class Persons {

        @Column(name = "id", primary = true)
        private int id;

        @Column(name = "firstName")
        private String name;

        @Column(name = "salary")
        private double salary;

    }

    Persons persons = new Persons();

    @Test
    public void getAll(){
        assertEquals("SELECT (id, firstName, salary) FROM TableName", queryGenerator.getAll(Persons.class));
    }

    @Test
    public void insert(){
        String email = "123@123.123";
        assertEquals("SELECT (id, firstName, salary) FROM TableName WHERE id=123@123.123", queryGenerator.getById(Persons.class, email));
    }

    @Test
    public void update() {
        assertEquals("UPDATE TableName SET id, firstName, salary", queryGenerator.update(persons));
    }

    @Test
    public void getById() throws InstantiationException, IllegalAccessException{
        assertEquals("INSERT INTO TableName (id, firstName, salary) VALUES (0, null, 0.0)", queryGenerator.insert(persons));
    }

    @Test
    public void delete(){
        Long digits = 987654321987654321L;
        assertEquals("DELETE (id, firstName, salary) FROM TableName WHERE id=987654321987654321", queryGenerator.delete(Persons.class, digits));
    }
}