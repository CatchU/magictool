package com.magic.juc;

import sun.applet.Main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 刘俊重
 * @Description
 * @date 11:36
 */
public class ThreadLocalTest {

    private static final ThreadLocal<DateFormat> THREAD_LOCAL = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public void format(){
        DateFormat dateFormat = THREAD_LOCAL.get();
        String format = dateFormat.format(new Date());
        System.out.println(format);
    }

    public static void main(String[] args) {
        ThreadLocalTest test = new ThreadLocalTest();
        test.format();
    }
}
