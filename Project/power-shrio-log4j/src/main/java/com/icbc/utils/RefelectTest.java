package com.icbc.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射操作类,通过bean工具类更简单的操作get、set方法
 *
 * @author chinoukin
 */
public class RefelectTest {

    public static void main(String[] args)
            throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Student s = new Student();
        s.setName("张三");
        s.setAge(20);

        String propertyName = "name";

        Object retVal = getPropertyVal(propertyName, s);
        System.out.println(retVal);

        String newVal = "李四";
        setPropertyVal(propertyName, s, newVal);
        System.out.println(s.getName());
    }

    private static void setPropertyVal(String propertyName, Object s, Object newVal)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        PropertyDescriptor pd = new PropertyDescriptor(propertyName, s.getClass());
        Method setMethod = pd.getWriteMethod();
        setMethod.invoke(s, newVal);
    }

    private static Object getPropertyVal(String propertyName, Object s)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        PropertyDescriptor pd = new PropertyDescriptor(propertyName, s.getClass());
        Method getMethod = pd.getReadMethod();
        Object retVal = getMethod.invoke(s);
        return retVal;
    }

}

/**
 * javabean
 *
 * @author chinoukin
 */
class Student {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}

