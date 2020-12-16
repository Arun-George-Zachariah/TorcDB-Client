package edu.missouri.Util;

import java.lang.reflect.Method;

public class CommonUtil {
    public static CommonUtil instance = null;

    private CommonUtil() {

    }

    public static CommonUtil getInstance() {
        if(instance == null) {
            instance = new CommonUtil();
        }
        return instance;
    }

    // Invoke a specified method on the object through reflection.
    public static Object invoke(Object object, String name, Class[] argClasses, Object... args) {
        System.out.println("CommonUtil :: invoke :: Start");
        Object out = null;
        try {
            Method method = object.getClass().getDeclaredMethod(name, argClasses);
            method.setAccessible(true);
            out = method.invoke(object, args);
        } catch (Exception e) {
            System.out.println("CommonUtil :: invoke :: Exception encountered.");
            e.printStackTrace();
        }
        System.out.println("CommonUtil :: invoke :: End");
        return out;
    }
}
