package com.magic.lang.comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 刘俊重
 * @Description
 * @date 12:56
 */
public class StudentComparableTest {
    public static void main(String[] args) {
        List<StudentComparable> students = new ArrayList<StudentComparable>();
        students.add(new StudentComparable("a",10));
        students.add(new StudentComparable("b",12));
        students.add(new StudentComparable("b",11));
        students.add(new StudentComparable("ac",20));
        Collections.sort(students);
        for(StudentComparable student : students) {
            System.out.println(student.getName()+" "+student.getAge());
        }
    }
}
