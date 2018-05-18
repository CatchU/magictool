package com.magic.lang.comparable;

/**
 * @author 刘俊重
 * @Description 学生比较器，实现comparable接口
 * 使用comparable比较器是直接让原有对象实现Comparable接口，重写compareTo方法
 * @date 12:52
 */
public class StudentComparable implements Comparable<StudentComparable>{

    /**
     * 如果名字相同则年龄排序
     * @param o
     * @return
     */
    @Override
    public int compareTo(StudentComparable o) {
        int flag = this.name.compareTo(o.getName());
        if(flag==0)
            flag=this.age.compareTo(o.getAge());
        return flag;
    }

    //姓名
    private String name;

    private Integer age;

    public StudentComparable(String name, Integer age) {
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
