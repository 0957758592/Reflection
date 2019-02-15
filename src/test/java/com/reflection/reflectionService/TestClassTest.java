package com.reflection.reflectionService;

class TestClassTest implements InterfaceTest {

    private String string = "string";
    private Integer integer = Integer.MAX_VALUE;
    private int intz = 123;
    private boolean boolzz = true;
    private short shortzz = 123;
    private long longzz = 987654321987L;
    private double doublezz = 123.123;
    private float floatzz = 123.123F;
    private char charzz = 'a';
    private Character charz = 'c';
    private Boolean bool = true;
    private Double doublez = 55.21;
    private Float floatz = 0.3F;
    private Long longz = 9876543216549L;
    private Short shortz = 2345;
    private Byte bytez = 123;

    public boolean isBoolzz() {
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

    @Override
    public Character getCharz() {
        return charz;
    }

    @Override
    public Short getShortz() {
        return shortz;
    }

    @Override
    public String getString() {
        return string;
    }

    @Override
    public Integer getInteger() {
        return integer;
    }

    @Override
    public Integer getInt() {
        return intz;
    }

    @Override
    public Boolean getBoolean() {
        return bool;
    }

    @Override
    public Double getDouble() {
        return doublez;
    }

    @Override
    public Float getFloat() {
        return floatz;
    }

    @Override
    public Long getLong() {
        return longz;
    }

    @Override
    public Byte getByte() {
        return bytez;
    }

    private Byte getBytes() {
        return bytez;
    }

    final void printMe() {
        System.out.println("private final void printMe");
    }

    final void printMeAgain() {
        System.out.println("final void printMeAgain");
    }
}

