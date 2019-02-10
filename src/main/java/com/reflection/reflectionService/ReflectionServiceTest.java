package com.reflection.reflectionService;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class ReflectionServiceTest {

    private class testClass implements myInteface {
        private String string = "string";
        private Integer integer = Integer.MAX_VALUE;
        private int intz = 123;
        private Boolean bool = true;
        private Double doublez = 55.21;
        private Float floatz = 0.3F;
        private Long longz = 9876543216549L;
        private Byte bytez = 123;

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
    }

    private interface myInteface {
        String getString();

        Integer getInteger();

        Integer getInt();

        Boolean getBoolean();

        Double getDouble();

        Float getFloat();

        Long getLong();

        Byte getByte();
    }

    String className = testClass.class.getSimpleName();

    @Before
    public void before() {


    }

    @Test
    public void getClassInstance() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println(ReflectionService.class.getDeclaredMethod("getClassInstance", String.class).invoke(className));
        assertEquals(testClass.class, ReflectionService.class.getDeclaredMethod("getClassInstance", String.class).invoke(className));
    }

    @Test
    public void getClassObject() {

    }

    @Test
    public void runMethodsWithoutParameters() {

    }

    @org.junit.Test
    public void runMethodsWithSignatureFinal() {

    }

    @Test
    public void printAllClassDeclaredMethods() {

    }

    @Test
    public void pintAllClassRelativesAndInterfaces() {

    }

    @Test
    public void modifyPrivateFields() {

    }
}