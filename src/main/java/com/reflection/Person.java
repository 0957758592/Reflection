package com.reflection;

import com.reflection.annotation.Column;
import com.reflection.annotation.Table;

@Table(name = "Perchik")
public class Person {

    @Column
    private int id;

    @Column(name = "firstName")
    private String name;

    @Column
    private double salary;

}
