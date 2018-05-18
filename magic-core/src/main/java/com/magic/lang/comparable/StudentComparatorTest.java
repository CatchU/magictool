package com.magic.lang.comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 刘俊重
 * @Description
 * @date 13:04
 */
public class StudentComparatorTest {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("a",10));
        students.add(new Student("b",12));
        students.add(new Student("b",11));
        students.add(new Student("ac",20));
        StudentComparator comparator = new StudentComparator();
        Collections.sort(students,comparator);
        for(Student student : students) {
            System.out.println(student.getName()+" "+student.getAge());
        }
    }
}
