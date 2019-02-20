package com.reflection.reflectionService.entity;

import java.io.Serializable;

public class TestClassTest extends AbstractTestClass implements Serializable {

    private String string = "string";
    private Integer integer = 123;
    private Character charz = 'c';
    private Boolean bool = true;
    private Double doublez = 123.123;
    private Float floatz = 123.123F;
    private Long longz = 123L;
    private Short shortz = 123;
    private Byte bytez = 123;
    private byte bytezz = 123;
    private int intz = 123;
    private boolean boolzz = true;
    private short shortzz = 123;
    private long longzz = 123L;
    private double doublezz = 123.123;
    private float floatzz = 123.123F;
    private char charzz = 'c';

    public String getString() {
        return string;
    }

    public Integer getInteger() {
        return integer;
    }

    public Character getCharz() {
        return charz;
    }

    public Boolean getBool() {
        return bool;
    }

    public Double getDoublez() {
        return doublez;
    }

    public Float getFloatz() {
        return floatz;
    }

    public Long getLongz() {
        return longz;
    }

    public Short getShortz() {
        return shortz;
    }

    public Byte getBytez() {
        return bytez;
    }

    public byte getBytezz() {
        return bytezz;
    }

    public int getIntz() {
        return intz;
    }

    public boolean getBoolzz() {
        return boolzz;
    }

    public short getShortzz() {
        return shortzz;
    }

    public long getLongzz() {
        return longzz;
    }

    public double getDoublezz() {
        return doublezz;
    }

    public float getFloatzz() {
        return floatzz;
    }

    public char getCharzz() {
        return charzz;
    }

    public void changeIntegerValue() {
        integer = 0;
    }

    public void changeBoleanValue() {
        bool = false;
    }

    public void changeStringValue() {
        string = null;
    }


    final void printMe() {
        System.out.println("private final void printMe");
    }

    final void printMeAgain() {
        System.out.println("final void printMeAgain");
    }
}

