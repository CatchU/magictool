package com.magic.highconcurrent.currentlimit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.CountDownLatch;

/**
 * @author 刘俊重
 * @Description 使用guava库的RateLimiter做一个令牌桶算法限流
 * 使用CountDownLatch做闭锁，目的是等工作线程执行完毕再执行主线程
 * 使用RateLimiter创建令牌桶，做高并发的限流，每秒产生5个令牌放进桶里，
 * 同时使用线程模拟100个请求，请求会阻塞，防止拖垮整个项目
 * @date 10:57
 */
public class CurrentLimitByRateLimiter {
    private static RateLimiter limiter = RateLimiter.create(10);
    private static CountDownLatch latch = new CountDownLatch(100);
    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
        for(int i=0;i<100;i++){
            Exec exec = new Exec();
            Thread thread = new Thread(exec);
            thread.start();
        }
        latch.await(); //注意一定要使用await方法而不是wait方法
        long end = System.currentTimeMillis();
        System.out.println("执行时间:"+(end-start)/1000+"s");
    }

    static class Exec implements Runnable{
        @Override
        public void run() {
            double acquire = limiter.acquire();
            try{
                //模拟核心业务
                System.out.println("处理核心业务逻辑。。。");
                latch.countDown();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
