package com.magic.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 刘俊重
 * @Description AtomicInteger能实现以原子方式进行自增自减，相当于Synchronized和Integer的结合
 * 适合使用在高并发中，但比Synchronized效率高
 * @date 14:22
 */
public class AtomicIntegerTest {
    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(0);
        for(int i=0;i<100;i++){
            count.incrementAndGet();
        }
        System.out.println("计数器是："+count.get());
        for(int j=100;j>0;j--){
            count.decrementAndGet();
        }
        System.out.println("计数器是："+count.get());
    }
}
