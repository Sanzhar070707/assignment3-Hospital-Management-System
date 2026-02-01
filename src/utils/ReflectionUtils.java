package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {
    public static void printClassInfo(Class<?> clazz) {
        System.out.println("Class: " + clazz.getName());

        System.out.println("Fields:");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(" - " + field.getName());
        }

        System.out.println("Methods:");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println(" - " + method.getName());
        }
    }
}