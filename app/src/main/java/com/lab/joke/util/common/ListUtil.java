package com.lab.joke.util.common;

import java.util.ArrayList;

/**
 * 集合帮助类
 */
public class ListUtil {
    public static <T> boolean isEmpty(ArrayList<T> list) {
        if (null != list && list.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public static <T> ArrayList<T> getList() {
        return new ArrayList<T>();
    }

    public static void main(String[] args) {
        ArrayList<Person> list = ListUtil.getList();
        Person person1 = new Person();
        person1.setName("张三");
        list.add(person1);

        for (Person person : list) {
            System.out.print(person.getName());
        }
    }

    public static class Person {
        private String name;
        private String age;

        public Person() {
        }

        @Override
        public String toString() {
            return "person{" +
                    "name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

}
