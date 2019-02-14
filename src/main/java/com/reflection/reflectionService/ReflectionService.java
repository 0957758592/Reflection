package com.reflection.reflectionService;

import com.reflection.ClassChecker;

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
        return clazz.newInstance();
    }

    //3
    public final void runMethodsWithoutParameters(Object object) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        checkIfIsNotNull(object);
        Class clazz = getClass(object);

        Method[] methods = getClassMethods(object.getClass());

        for (Method method : methods) {

            if (Modifier.isPrivate(method.getModifiers())) {
                method.setAccessible(true);
                if (method.getParameterCount() == 0) {
                    method.invoke(object);
                }
                method.setAccessible(false);
            }
        }
    }

    //4
    public void printMethodsWithSignatureFinal(Object object) {
        checkIfIsNotNull(object);

        Class clazz = getClass(object);

        Method[] methods = getClassMethods(clazz);

        for (Method method : methods) {
            printModifier(method);
        }
    }

    //5
    public void printAllPrivateMethods(Class<?> clazz) {
        checkIfIsNotNull(clazz);

        Method[] methods = getClassMethods(clazz);
        StringJoiner sj = new StringJoiner(", ");
        for (Method method : methods) {
            if (Modifier.isPrivate(method.getModifiers())) {
                sj.add(method.getName());
            }
        }
        System.out.print(sj.toString());
    }

    //6
    public void pintAllClassRelativesAndInterfaces(Class<?> clazz) {
        checkIfIsNotNull(clazz);

        for (Class<?> aClass : clazz.getInterfaces()) {
            System.out.print(aClass.getSimpleName() + " ");
        }

        if (clazz.getSuperclass() != null) {
            Class superClass = clazz.getSuperclass();
            System.out.print(superClass.getSimpleName());
            pintAllClassRelativesAndInterfaces(superClass);
        }

    }

    //7
    public void modifyPrivateFields(Object instance) throws IllegalAccessException {
        checkIfIsNotNull(instance);

        Field[] fields = ClassChecker.getClassFields(instance.getClass());

        for (Field field : fields) {

            if (Modifier.isPrivate(field.getModifiers())) {

                field.setAccessible(true);

                if (field.getType().equals(String.class)) {
                    field.set(instance, null);
                } else if (field.getType().equals(Integer.class)) {
                    field.set(instance, 0);
                } else if (field.getType().equals(int.class)) {
                    field.set(instance, 0);
                } else if (field.getType().equals(Boolean.class)) {
                    field.set(instance, false);
                } else if (field.getType().equals(boolean.class)) {
                    field.set(instance, false);
                } else if (field.getType().equals(Double.class)) {
                    field.set(instance, 0.0);
                } else if (field.getType().equals(double.class)) {
                    field.set(instance, 0.0);
                } else if (field.getType().equals(Float.class)) {
                    field.set(instance, 0f);
                } else if (field.getType().equals(Long.class)) {
                    field.set(instance, 0L);
                } else if (field.getType().equals(Character.class)) {
                    field.set(instance, (char) 0);
                } else if (field.getType().equals(char.class)) {
                    field.set(instance, (char) 0);
                } else if (field.getType().equals(Short.class)) {
                    field.set(instance, (short) 0);
                } else if (field.getType().equals(short.class)) {
                    field.set(instance, (short) 0);
                } else if (field.getType().equals(Byte.class)) {
                    field.set(instance, (byte) 0);
                } else if (field.getType().equals(byte.class)) {
                    field.set(instance, (byte) 0);
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

    private void printModifier(Method method) {
        if (Modifier.isFinal(method.getModifiers())) {
            if (method.getParameterCount() == 0) {
                System.out.print("la finale: " + method.getName() + " ");
            } else {
                StringJoiner sj = new StringJoiner(", ", "(", ")");
                for (Class<?> parameterType : method.getParameterTypes()) {
                    sj.add(parameterType.getSimpleName());
                }
                System.out.println(method.getName() + sj);
            }
        }
    }

    private Class getClass(Object object) {
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