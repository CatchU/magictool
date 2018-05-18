package com.magic.lang.comparable;

import java.util.Comparator;

/**
 * @author 刘俊重
 * @Description 这种方式是直接实现一个Comparator接口，
 * 重写compare方法，不用修改原对象。也可以写成匿名函数的形式
 * @date 13:01
 */
public class StudentComparator implements Comparator<Student>{

    /**
     * 如果姓名一样则比较年龄
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Student o1, Student o2) {
        int flag = o1.getName().compareTo(o2.getName());
        if(flag==0)
            flag = o1.getAge().compareTo(o2.getAge());
        return flag;
    }
}
