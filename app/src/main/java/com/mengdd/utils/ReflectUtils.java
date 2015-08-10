package com.mengdd.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 *
 * @ClassName ReflectUtils
 * @Description Reflection Helper class
 *
 * @author mengdandan
 * @Date 2014年5月13日上午10:40:32
 *
 */
public class ReflectUtils {

    /**
     * 创建类的实例，调用类的无参构造方法
     *
     * @param className
     * @return
     */
    public static Object newInstance(String className) {

        Object instance = null;

        try {
            Class<?> clazz = Class.forName(className);
            instance = clazz.newInstance();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            // if this Class represents an abstract class, an interface, an
            // array class, a primitive type, or void; or if the class has no
            // nullary constructor; or if the instantiation fails for some other
            // reason.

            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            // if the class or its nullary constructor is not accessible
            e.printStackTrace();
        }

        return instance;

    }

    /**
     * 获取所有的public构造方法的信息
     *
     * @param className
     * @return
     */
    public static String getPublicConstructorInfo(String className) {
        StringBuilder sBuilder = new StringBuilder();

        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?>[] constructors = clazz.getConstructors();
            sBuilder.append(getConstructorInfo(constructors));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return sBuilder.toString();
    }

    /**
     * 得到本类内声明的构造方法信息
     *
     * @param className
     * @return
     */
    public static String getDeclearedConstructorInfo(String className) {
        StringBuilder sBuilder = new StringBuilder();

        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            sBuilder.append(getConstructorInfo(constructors));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return sBuilder.toString();
    }

    /**
     * 获取public的字段信息
     *
     * @param className
     * @return
     */
    public static String getPublicFieldInfo(String className) {
        StringBuilder sBuilder = new StringBuilder();

        try {
            Class<?> clazz = Class.forName(className);

            Field[] fields = clazz.getFields();
            sBuilder.append(getFieldInfo(fields));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return sBuilder.toString();
    }

    /**
     * 获取本类内声明的字段信息
     *
     * @param className
     * @return
     */
    public static String getDecleardFieldInfo(String className) {
        StringBuilder sBuilder = new StringBuilder();

        try {
            Class<?> clazz = Class.forName(className);

            Field[] fields = clazz.getDeclaredFields();
            sBuilder.append(getFieldInfo(fields));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return sBuilder.toString();
    }

    /**
     * 得到所有public方法信息
     *
     * @param className
     * @return
     */
    public static String getPublicMethodInfos(String className) {
        StringBuilder sBuilder = new StringBuilder();

        try {
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getMethods();// 得到所有的public方法，包括从基类继承的

            sBuilder.append(getMethodInfo(methods));

        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return sBuilder.toString();
    }

    /**
     * 得到类内声明的方法信息
     *
     * @param className
     * @return
     */
    public static String getDeclaredMethodInfos(String className) {

        StringBuilder sBuilder = new StringBuilder();
        try {
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getDeclaredMethods();// 得到本类声明的所有方法,包括私有方法
            // clazz.getMethods(); 会返回所有public的方法，但是包括基类Object的方法

            sBuilder.append(getMethodInfo(methods));

        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return sBuilder.toString();
    }

    /**
     * 得到构造器信息
     *
     * @param constructor
     * @return
     */
    private static String getConstructorInfo(Constructor<?> constructor) {

        StringBuilder sBuilder = new StringBuilder();

        sBuilder.append("name: " + constructor.getName());
        sBuilder.append("\ngetParameterTypes: "
                + Arrays.toString(constructor.getParameterTypes()));
        return sBuilder.toString();
    }

    /**
     * 将一组构造器的信息组成一个字符串返回
     *
     * @param constructors
     * @return
     */
    private static String getConstructorInfo(Constructor<?>[] constructors) {

        StringBuilder sBuilder = new StringBuilder();
        int i = 0;
        for (Constructor<?> c : constructors) {
            sBuilder.append("method: " + ++i + " : ");
            sBuilder.append("\n" + getConstructorInfo(c));
            sBuilder.append("\n");
        }

        return sBuilder.toString();

    }

    /**
     * 获取字段信息，组成一个字符串返回
     *
     * @param field
     * @return
     */
    private static String getFieldInfo(Field field) {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("name: " + field.getName());
        sBuilder.append("\ngetType: " + field.getType());
        sBuilder.append(getModifiersInfo(field));
        return sBuilder.toString();
    }

    /**
     * 获取一组字段的信息，返回字符串
     *
     * @param fields
     * @return
     */
    private static String getFieldInfo(Field[] fields) {
        StringBuilder sBuilder = new StringBuilder();
        int i = 0;
        for (Field field : fields) {
            sBuilder.append("field: " + ++i + " : ");
            sBuilder.append("\n" + getFieldInfo(field));
            sBuilder.append("\n");
        }

        return sBuilder.toString();
    }

    /**
     * 获取方法的信息，组成一个字符串返回
     *
     * @param method
     * @return
     */
    private static String getMethodInfo(Method method) {

        StringBuilder sBuilder = new StringBuilder();

        sBuilder.append("name: " + method.getName());
        sBuilder.append("\ngetReturnType: " + method.getReturnType());
        sBuilder.append("\ngetParameterTypes: "
                + Arrays.toString(method.getParameterTypes()));
        sBuilder.append(getModifiersInfo(method));
        return sBuilder.toString();
    }

    /**
     * 获取一组方法的信息，组成一个字符串返回
     *
     * @param methods
     * @return
     */
    private static String getMethodInfo(Method[] methods) {
        StringBuilder sBuilder = new StringBuilder();
        int i = 0;
        for (Method method : methods) {

            sBuilder.append("method: " + ++i + " : ");
            sBuilder.append("\n" + getMethodInfo(method));
            sBuilder.append("\n");

        }

        return sBuilder.toString();
    }

    /**
     * 获取修饰符信息
     *
     * @param member
     * @return
     */
    private static String getModifiersInfo(Member member) {
        StringBuilder sBuilder = new StringBuilder();
        int modifiers = member.getModifiers();
        sBuilder.append("\ngetModifiers: " + +modifiers + ", ");// 得到修饰符编码
        sBuilder.append("\nisPublic: " + Modifier.isPublic(modifiers) + ", ");
        sBuilder.append("\nisPrivate: " + Modifier.isPrivate(modifiers) + ", ");
        sBuilder.append("\nisStatic: " + Modifier.isStatic(modifiers) + ", ");
        sBuilder.append("\nisFinal: " + Modifier.isFinal(modifiers) + ", ");
        sBuilder.append("\nisAbstract: " + Modifier.isAbstract(modifiers));

        return sBuilder.toString();
    }

    /**
     * 是否是公用静态方法
     *
     * @param member
     * @return
     */
    private static boolean isPublicStatic(Member member) {
        boolean isPS = false;
        int mod = member.getModifiers();
        isPS = Modifier.isPublic(mod) && Modifier.isStatic(mod);
        return isPS;
    }

    /**
     * 调用静态方法
     *
     * @param className
     * @param methodName
     * @param paramTypes
     * @param params
     * @return
     * @throws Exception
     */
    public static Object invokePublicStaticMethod(String className,
            String methodName, Class<?>[] paramTypes, Object[] params)
            throws Exception {

        Class<?> cls = Class.forName(className);

        Method method = cls.getMethod(methodName, paramTypes);
        Object value = null;
        if (isPublicStatic(method)) {
            value = method.invoke(null, params);
        }

        return value;
    }

    /**
     * 调用私有方法
     *
     * @param obj
     *            调用类对象
     * @param methodName
     *            方法名
     * @param paramTypes
     *            参数类型
     * @param params
     *            参数
     * @return
     * @throws Exception
     */
    public static Object invokePrivateMethod(Object obj, String methodName,
            Class<?>[] paramTypes, Object[] params) throws Exception {

        Object value = null;
        Class<?> cls = obj.getClass();

        // 注意不要用getMethod(),因为getMethod()返回的都是public方法
        Method method = cls.getDeclaredMethod(methodName, paramTypes);

        method.setAccessible(true);// 抑制Java的访问控制检查

        value = method.invoke(obj, params);
        return value;
    }
}
