package com.reflection;

import com.reflection.annotation.Column;
import com.reflection.annotation.Table;

@Table(name = "TableName")
public class Person {

    @Column(name = "id")
    private int id;

    @Column(name = "firstName")
    private String name;

    @Column(name = "salary")
    private double salary;

}
