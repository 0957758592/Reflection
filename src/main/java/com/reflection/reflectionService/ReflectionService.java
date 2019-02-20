package com.reflection.reflectionService;

import java.lang.reflect.*;
import java.util.StringJoiner;

public class ReflectionService {

    //1
    public Object getClassInstance(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        checkIfIsNotNull(name);
        return Class.forName(name).newInstance();
    }

    //2
    public Object getClassObject(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        checkIfIsNotNull(clazz);
        return clazz.newInstance();
    }

    //3
    public final void runMethodsWithoutParameters(Object object) throws InvocationTargetException, IllegalAccessException {
        checkIfIsNotNull(object);

        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.getParameterCount() == 0) {
                if (Modifier.isPrivate(method.getModifiers()) || Modifier.isFinal(method.getModifiers())) {
                    method.setAccessible(true);
                }
                method.invoke(object);
            }
        }
    }

    //4
    public void printFinalMethodsSignature(Object object) {
        checkIfIsNotNull(object);

        for (Method method : object.getClass().getDeclaredMethods()) {
            printSignatures(method);
        }
    }

    //5
    public void printAllPrivateMethods(Class<?> clazz) {
        checkIfIsNotNull(clazz);

        for (Method method : clazz.getDeclaredMethods()) {
            if (!Modifier.isPublic(method.getModifiers())) {
                printMethodsAndParameters(method);
            }
        }
    }

    //6
    public void pintAllClassRelativesAndInterfaces(Class<?> clazz) {
        checkIfIsNotNull(clazz);

        for (Class<?> aClass : clazz.getInterfaces()) {
            System.out.println(aClass.getSimpleName());
        }

        if (clazz.getSuperclass() != null) {
            Class superClass = clazz.getSuperclass();
            System.out.println(superClass.getSimpleName());
            pintAllClassRelativesAndInterfaces(superClass);
        }

    }

    //7
    public void modifyPrivateFields(Object instance) throws IllegalAccessException {
        checkIfIsNotNull(instance);

        Field[] fields = instance.getClass().getDeclaredFields();

        for (Field field : fields) {

            if (Modifier.isPrivate(field.getModifiers())) {

                field.setAccessible(true);

                if (field.getType().equals(Integer.class)) {
                    field.set(instance, 0);
                } else if (field.getType().equals(int.class)) {
                    field.setInt(instance, 0);
                } else if (field.getType().equals(Boolean.class)) {
                    field.set(instance, false);
                } else if (field.getType().equals(boolean.class)) {
                    field.setBoolean(instance, false);
                } else if (field.getType().equals(Double.class)) {
                    field.set(instance, 0.0);
                } else if (field.getType().equals(double.class)) {
                    field.setDouble(instance, 0.0);
                } else if (field.getType().equals(Float.class)) {
                    field.set(instance, 0f);
                } else if (field.getType().equals(float.class)) {
                    field.setFloat(instance, 0f);
                } else if (field.getType().equals(Long.class)) {
                    field.set(instance, 0L);
                } else if (field.getType().equals(long.class)) {
                    field.setLong(instance, 0L);
                } else if (field.getType().equals(Character.class)) {
                    field.set(instance, (char) 0);
                } else if (field.getType().equals(char.class)) {
                    field.setChar(instance, (char) 0);
                } else if (field.getType().equals(Short.class)) {
                    field.set(instance, (short) 0);
                } else if (field.getType().equals(short.class)) {
                    field.setShort(instance, (short) 0);
                } else if (field.getType().equals(Byte.class)) {
                    field.set(instance, (byte) 0);
                } else if (field.getType().equals(byte.class)) {
                    field.setByte(instance, (byte) 0);
                } else {
                    field.set(instance, null);
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

    private void printSignatures(Method method) {
        if (Modifier.isFinal(method.getModifiers())) {
            printMethodsAndParameters(method);

        }
    }

    private void printMethodsAndParameters(Method method) {
        StringJoiner sj = new StringJoiner(", ", "(", ")");
        for (Class<?> parameterType : method.getParameterTypes()) {
            sj.add(parameterType.getSimpleName());
        }
        System.out.println(method.getName() + sj);
    }

}