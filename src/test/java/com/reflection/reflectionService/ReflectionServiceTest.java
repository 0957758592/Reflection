package com.reflection.reflectionService;

import com.reflection.reflectionService.entity.TestClassTest;
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
    private ReflectionService reflectionService;
    private TestClassTest testClassTest = new TestClassTest();
    Object instance;

    @Before
    public void before() {
        System.setOut(new PrintStream(outContent));
        reflectionService = new ReflectionService();

    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void getClassInstance() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        //prepare
        instance = reflectionService.getClassInstance(className);
        //then
        assertEquals(TestClassTest.class, instance.getClass());
    }

    @Test
    public void getClassObject() throws InstantiationException, IllegalAccessException {
        //prepare
        instance = reflectionService.getClassObject(testClassTest.getClass());
        //then
        assertEquals(TestClassTest.class, instance.getClass());
    }

    @Test
    public void runMethodsWithoutParameters() throws IllegalAccessException, InvocationTargetException {
        //prepare
        assertTrue(testClassTest.getBool());
        assertNotNull(testClassTest.getString());
        assertEquals(123, (int) testClassTest.getInteger());


        reflectionService.runMethodsWithoutParameters(testClassTest);

        //then
        assertFalse(testClassTest.getBool());
        assertNull(testClassTest.getString());
        assertEquals(0, (int) testClassTest.getInteger());
    }

    @Test
    public void printFinalMethodsSignature() {
        //prepare
        reflectionService.printFinalMethodsSignature(testClassTest);

        //then
        assertEquals("printMe()\nprintMeAgain()\n", outContent.toString());
    }

    @Test
    public void printAllClassDeclaredMethods() {
        //prepare
        reflectionService.printAllPrivateMethods(testClassTest.getClass());

        //then
        assertEquals("printMe()\nprintMeAgain()\n", outContent.toString());
    }

    @Test
    public void pintAllClassRelativesAndInterfaces() {
        //prepare
        reflectionService.pintAllClassRelativesAndInterfaces(testClassTest.getClass());

        //then
        assertEquals("Serializable\nAbstractTestClass\nCloneable\nObject\n", outContent.toString());
    }


    @Test
    public void modifyPrivateFields() throws IllegalAccessException {
        assertEquals("string", testClassTest.getString());
        assertEquals(123, (int) testClassTest.getInteger());
        assertEquals(123, testClassTest.getIntz());
        assertTrue(testClassTest.getBool());
        assertTrue(testClassTest.getBoolzz());
        assertEquals('c', (char) testClassTest.getCharz());
        assertEquals('c', testClassTest.getCharzz());
        assertEquals((Double) 123.123, testClassTest.getDoublez());
        assertEquals(123.123, testClassTest.getDoublezz(), 0.00001);
        assertEquals((Float) 123.123F, testClassTest.getFloatz());
        assertEquals(123.123F, testClassTest.getFloatzz(), 0.00001);
        assertEquals(123L, (long) testClassTest.getLongz());
        assertEquals(123L, testClassTest.getLongzz());
        assertEquals(123, (short) testClassTest.getShortz());
        assertEquals(123, testClassTest.getShortzz());
        assertEquals(123, (byte) testClassTest.getBytez());
        assertEquals(123, testClassTest.getBytezz());

        reflectionService.modifyPrivateFields(testClassTest);

        assertNull(testClassTest.getString());
        assertEquals(0, (int) testClassTest.getInteger());
        assertEquals(0, testClassTest.getIntz());
        assertFalse(testClassTest.getBool());
        assertFalse(testClassTest.getBoolzz());
        assertEquals(0, (char) testClassTest.getCharz());
        assertEquals(0, testClassTest.getCharzz());
        assertEquals((Double) 0.0, testClassTest.getDoublez());
        assertEquals(0.0, testClassTest.getDoublezz(), 0.00001);
        assertEquals((Float) 0.0F, testClassTest.getFloatz());
        assertEquals(0.0F, testClassTest.getFloatzz(), 0.00001);
        assertEquals(0L, (long) testClassTest.getLongz());
        assertEquals(0L, testClassTest.getLongzz());
        assertEquals(0, (short) testClassTest.getShortz());
        assertEquals(0, testClassTest.getShortzz());
        assertEquals(0, (byte) testClassTest.getBytez());
        assertEquals(0, testClassTest.getBytezz());

    }
}