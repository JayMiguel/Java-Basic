package com.miguel.server.basic;

/**
 * 反射获取Class对象的三种方式
 * 1.通过已有对象获取Class对象
 * 2.通过类名获取Class对象
 * 3.通过全路径获取Class对象
 */
public class Reflection {

    public static void main(String[] args) throws ClassNotFoundException {
        //方式一
        Person person = new Person();
        Class<Person> clazz1 = (Class<Person>) person.getClass();
        //方式二
        Class<Person> clazz2 = Person.class;
        //方式三
        Class<Person> clazz3 = (Class<Person>) Class.forName("com.miguel.server.basic.Person");
    }
}
