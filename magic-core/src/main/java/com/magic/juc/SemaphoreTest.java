package com.magic.juc;

import java.util.concurrent.*;

/**
 * @author 刘俊重
 * @Description Semaphore中文名称是信号量，它可以用作计数器，
 * 也可以当做锁来用，主要是限制资源的进入，也可以用作限流。
 * 类似于synchornized，acquire获取资源，release释放资源
 * @date 16:01
 */
public class SemaphoreTest {
    /**
     * 10个工人用5个机器
     * @param args
     */
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,
                5, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        try{
            for(int i=0;i<10;i++){
                poolExecutor.execute(new Worker(semaphore,"张三"+i));
            }
        }finally {
            poolExecutor.shutdown();
        }
    }

    static class Worker implements Runnable{

        private Semaphore semaphore;

        private String name;

        public Worker(Semaphore semaphore, String name) {
            this.semaphore = semaphore;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人："+name+"开始用机器干活");
                Thread.sleep(100);
                semaphore.release();
                System.out.println("工人："+name+"用完了机器，释放=====");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
