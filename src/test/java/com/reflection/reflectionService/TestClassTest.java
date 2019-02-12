package com.reflection.reflectionService;

class TestClassTest implements InterfaceTest {

    private String string = "string";
    private Integer integer = Integer.MAX_VALUE;
    private int intz = 123;
    private Character charz = 'c';
    private Boolean bool = true;
    private Double doublez = 55.21;
    private Float floatz = 0.3F;
    private Long longz = 9876543216549L;
    private Short shortz = 2345;
    private Byte bytez = 123;

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

    final void printMe() {
        System.out.println("private final void printMe");
    }

    final void printMeAgain() {
        System.out.println("final void printMeAgain");
    }
}

