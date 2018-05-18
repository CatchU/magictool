package com.magic.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author 刘俊重
 * @Description CountDownLatch是闭锁，
 * 代表的是一个线程要等待其它线程执行完毕之后才开始执行,注意：是await方法而不是wait方法
 * @date 14:44
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(10);

        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        System.out.println(Thread.currentThread().getName()+"线程开始执行");
                        Thread.sleep(100);
                        System.out.println(Thread.currentThread().getName()+"线程指定完毕");
                        latch.countDown();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        try{
            System.out.println("程序等其它线程执行=======");
            latch.await();
            System.out.println("结束");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
