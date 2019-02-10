package com.reflection.reflectionService;

import com.reflection.AbstractClassChecker;

import java.lang.reflect.*;


public class ReflectionService extends AbstractClassChecker {

    //1
    public Object getClassInstance(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        checkIfIsNotNull(name);
        isClassExist(name);
        return Class.forName(name).newInstance();
    }

    //2 ???
    public Object getClassObject(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        isClassExist(clazz.getName());
        return clazz.newInstance();
    }

    //3
    public final void runMethodsWithoutParameters(Object object) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        checkIfIsNotNull(object);
        Class clazz = getInstanceOfObject(object);
        Object instance = clazz.newInstance();

        Field[] fields = getClassFields(clazz);

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(instance) == null) {

                Object newValue = field.getType().cast(Integer.toString(111));
                field.set(instance, newValue);
            }
            field.setAccessible(false);
        }

        Method[] methods = getClassMethods(clazz);

        for (Method method : methods) {

            if (Modifier.isPrivate(method.getModifiers())) {
                method.setAccessible(true);
            }
            if (method.getGenericParameterTypes().length == 0) {
                method.invoke(instance);
            }
            method.setAccessible(false);
        }
    }

    //4
    public void runMethodsWithSignatureFinal(Object object) throws InvocationTargetException, IllegalAccessException {
        checkIfIsNotNull(object);

        Class clazz = getInstanceOfObject(object);

        Method[] methods = getClassMethods(clazz);

        for (Method method : methods) {
            printModifier(method);
        }
    }

    //5
    public void printAllClassDeclaredMethods(Class<?> clazz) {
        checkIfIsNotNull(clazz);
        isClassExist(clazz.getName());

        Method[] methods = getClassMethods(clazz);

        for (Method method : methods) {
            System.out.println(method);
        }
    }

    //6
    public void pintAllClassRelativesAndInterfaces(Class<?> clazz) {
        checkIfIsNotNull(clazz);
        isClassExist(clazz.getName());

        for (Class<?> aClass : clazz.getInterfaces()) {
            System.out.println(aClass.getName());
        }

        if (clazz.getSuperclass() != null) {
            Class superClass = clazz.getSuperclass();
            System.out.println(superClass.getSimpleName());
            pintAllClassRelativesAndInterfaces(superClass);
        }

    }

    //7
    public void modifyPrivateFields(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        checkIfIsNotNull(clazz);
        isClassExist(clazz.getName());

        Field[] fields = getClassFields(clazz);
        Object instance;

        for (Field field : fields) {

            if (Modifier.isPrivate(field.getModifiers())) {
                instance = clazz.newInstance();

                field.setAccessible(true);

                if (field.getType().equals(String.class)) {
                    field.set(instance, null);
                } else if (field.getType().equals(Integer.class)) {
                    field.set(instance, 0);
                } else if (field.getType().equals(Boolean.class)) {
                    field.set(instance, false);
                } else if (field.getType().equals(Double.class)) {
                    field.set(instance, 0.0);
                } else if (field.getType().equals(Float.class)) {
                    field.set(instance, 0f);
                } else if (field.getType().equals(Long.class)) {
                    field.set(instance, 0L);
                } else if (field.getType().equals(Character.class)) {
                    field.set(instance, (char) 0);
                } else if (field.getType().equals(int.class)) {
                    field.set(instance, 0);
                } else if (field.getType().equals(Short.class)) {
                    field.set(instance, (short) 0);
                } else if (field.getType().equals(Byte.class)) {
                    field.set(instance, (byte) 0);
                }

                field.setAccessible(false);
            }

        }

    }


    private void checkIfIsNotNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("The object can't be a null");
        }
    }

    private void printModifier(Method method) {
        if (Modifier.isFinal(method.getModifiers())) {
            System.out.println("la finale: " + Modifier.toString(method.getModifiers()));
        }
    }

    private Class getInstanceOfObject(Object object) {
        return (object instanceof Class) ? (Class) object : object.getClass();
    }


    private Method[] getClassMethods(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();

        if (methods == null) {
            throw new NullPointerException("There are no any methods at the class");
        }
        return methods;
    }


}