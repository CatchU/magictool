package com.magic.thread;

/**
 * @author 刘俊重
 * @Description
 * @date 9:51
 */
public class DeadLock {

    private static Object left = new Object();

    private static Object right = new Object();

    public void leftRight() throws Exception{
        synchronized (left){
            System.out.println("获得了Left锁，等待获取Right锁");
            Thread.sleep(2000);
            synchronized (right){
                System.out.println("获得了Right锁");
            }
        }
    }

    public void rightLeft() throws Exception{
        synchronized (right){
            System.out.println("获得了Right锁，等待获取Left锁");
            Thread.sleep(2000);
            synchronized (left){
                System.out.println("获得了Left锁");
            }
        }
    }
}
