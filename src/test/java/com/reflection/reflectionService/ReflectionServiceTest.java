package com.reflection.reflectionService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class ReflectionServiceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private String className = TestClassTest.class.getName();
    private ReflectionService sReflectionService;
    TestClassTest testClassTest = new TestClassTest();

    @Before
    public void before() {
        System.setOut(new PrintStream(outContent));
        sReflectionService = new ReflectionService();
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void getClassInstance() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        assertEquals(TestClassTest.class.getName(), sReflectionService.getClassInstance(className).toString().substring(0, sReflectionService.getClassInstance(className).toString().lastIndexOf("@")));
    }

    @Test
    public void getClassObject() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        assertEquals(TestClassTest.class.getName(), sReflectionService.getClassInstance(className).toString().substring(0, sReflectionService.getClassObject(TestClassTest.class).toString().lastIndexOf("@")));

    }

    @Test
    public void runMethodsWithoutParameters() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        sReflectionService.runMethodsWithoutParameters(TestClassTest.class);
//      assertTrue(sReflectionService.runMethodsWithoutParameters(TestClassTest.class));
//Mokito
    }

    @Test
    public void runMethodsWithSignatureFinal() {
        sReflectionService.runMethodsWithSignatureFinal(testClassTest);
        assertEquals("la finale: printMe la finale: printMeAgain ", outContent.toString());
    }

    @Test
    public void printAllClassDeclaredMethods() {
        sReflectionService.printAllClassDeclaredMethods(testClassTest.getClass());
        assertEquals("getString, getCharz, getShortz, printMe, printMeAgain, getBoolean, getByte, getInt, getLong, getFloat, getDouble, getInteger", outContent.toString());
    }

    @Test
    public void pintAllClassRelativesAndInterfaces() {
        sReflectionService.pintAllClassRelativesAndInterfaces(testClassTest.getClass());
        assertEquals("InterfaceTest Object", outContent.toString());
    }

    @Test
    public void modifyPrivateFields() throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        assertEquals("string", testClassTest.getString());
        assertEquals((Integer) Integer.MAX_VALUE, testClassTest.getInteger());
        assertEquals((Integer) 123, testClassTest.getInt());
        assertEquals(true, testClassTest.getBoolean());
        assertEquals((Character) 'c', testClassTest.getCharz());
        assertEquals((Double) 55.21, testClassTest.getDouble());
        assertEquals((Float) 0.3F, testClassTest.getFloat());
        assertEquals((Long) 9876543216549L, testClassTest.getLong());
        assertEquals(String.valueOf(2345), String.valueOf(testClassTest.getShortz()));
        assertEquals(String.valueOf(123), String.valueOf(testClassTest.getByte()));

        sReflectionService.modifyPrivateFields(testClassTest);

        assertEquals(null, testClassTest.getString());
        assertEquals((Integer) 0, testClassTest.getInteger());
        assertEquals((Integer) 0, testClassTest.getInt());
        assertEquals(false, testClassTest.getBoolean());
        assertEquals((char) 0, (char) testClassTest.getCharz());
        assertEquals((Double) 0.0, testClassTest.getDouble());
        assertEquals((Float) 0.0F, testClassTest.getFloat());
        assertEquals((Long) 0L, testClassTest.getLong());
        assertEquals(String.valueOf(0), String.valueOf(testClassTest.getShortz()));
        assertEquals(String.valueOf(0), String.valueOf(testClassTest.getByte()));

    }
}