package com.reflection.queryGenerator;

import com.reflection.annotation.Column;
import com.reflection.annotation.Table;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueryGeneratorTest {

        private QueryGenerator queryGenerator = new QueryGenerator();
        private static final String GET_ALL = "SELECT (id, firstName, salary) FROM person;";
        private static final String INSERT = "INSERT INTO person (id, firstName, salary) VALUES (1, \"Kate\", 55.6);";
        private static final String UPDATE = "UPDATE person SET id = 1, firstName = \"Kate\", salary = 55.6 WHERE id = 2;";
        private static final String GET_BY_ID = "SELECT (id, firstName, salary) FROM person WHERE id = 1;";
        private static final String DELETE = "DELETE FROM person WHERE id = 1;";

        private Person person;

        @Table(name = "person")
        static class Person {

            @Column(name = "id", primary = true)
            private int id;

            @Column(name = "firstName")
            private String name;

            @Column(name = "salary")
            private double salary;

            private int age;
        }

        @Before
        public void before(){
            //prepare
            person = new Person();
            person.id = 1;
            person.name = "Kate";
            person.salary = 55.6;

        }

        @Test
        public void getAll(){
            assertEquals(GET_ALL, queryGenerator.getAll(Person.class));
        }

        @Test
        public void insert() throws IllegalAccessException {
            assertEquals(INSERT, queryGenerator.insert(person));
        }

        @Test
        public void update() throws IllegalAccessException {
            assertEquals(UPDATE, queryGenerator.update(person, 2));
        }

        @Test
        public void getById() {
            assertEquals(GET_BY_ID, queryGenerator.getById(Person.class, 1));
        }

        @Test
        public void delete(){
            assertEquals(DELETE, queryGenerator.delete(Person.class, 1));
        }

}