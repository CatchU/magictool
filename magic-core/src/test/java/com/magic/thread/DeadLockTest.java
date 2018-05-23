package com.magic.thread;

import org.junit.Test;

/**
 * @author 刘俊重
 * @Description
 * @date 9:57
 */
public class DeadLockTest {
    class LeftRightThread extends Thread{
        private DeadLock deadLock;

        public LeftRightThread(DeadLock deadLock) {
            this.deadLock = deadLock;
        }

        @Override
        public void run(){
            try {
                deadLock.leftRight();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class RightLeftThread extends Thread{
        private DeadLock deadLock;

        public RightLeftThread(DeadLock deadLock){
            this.deadLock = deadLock;
        }

        @Override
        public void run() {
            try {
                deadLock.rightLeft();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //测试时再放开，不然死锁，会导致其它程序也没法运行
//    @Test
//    public void test(){
//        DeadLock deadLock = new DeadLock();
//        LeftRightThread leftRightThread = new LeftRightThread(deadLock);
//        RightLeftThread rightLeftThread = new RightLeftThread(deadLock);
//
//        leftRightThread.start();
//        rightLeftThread.start();
//
//        while (true);
//    }
}
