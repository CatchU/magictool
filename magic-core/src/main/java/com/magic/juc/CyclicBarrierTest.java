package com.magic.juc;

import java.util.concurrent.*;

/**
 * @author 刘俊重
 * @Description CyclicBarrier是一组线程里面的线程
 * 互相等待其它线程就绪才开始执行，强调一个互相等待
 * @date 15:21
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        //poolExecutor poolExecutor = Executors.newFixedThreadPool(5);
        //创新核心线程数是5，最大线程数是5，保持5秒不被回收，时间是以秒为单位，链表阻塞队列
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,
                5, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        try{
            for(int i=0;i<5;i++){
                poolExecutor.execute(new Runner(cyclicBarrier, "张三"+i));
            }
        }finally {
            poolExecutor.shutdown();
        }
    }

    static class Runner implements Runnable{

        private CyclicBarrier cyclicBarrier;

        private String name;

        public Runner(CyclicBarrier cyclicBarrier, String name) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(name+"做好准备");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(name+"开始跑");
        }
    }
}
