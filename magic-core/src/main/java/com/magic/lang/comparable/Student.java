package com.magic.lang.comparable;

/**
 * @author 刘俊重
 * @Description 学生对象
 * @date 12:50
 */
public class Student {
    //姓名
    private String name;
    //年龄
    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
