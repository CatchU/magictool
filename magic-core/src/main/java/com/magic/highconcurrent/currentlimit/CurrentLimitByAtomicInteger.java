package com.magic.highconcurrent.currentlimit;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 刘俊重
 * @Description 通过AtomicInteger限流（计数器算法）
 * AtomicInteger 作为计数器，是原子操作，是线程安全的，可以使用它完成线程安全的自增自减
 * 如果计数器超过10则不再处理请求，从而进行限流操作。
 * @date 13:44
 */
public class CurrentLimitByAtomicInteger {

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        for(int i=0;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(count.get()>10){
                        System.out.println("请求太多，稍后再重试");
                    }else{
                        count.incrementAndGet();
                        try{
                            //睡5秒
                            Thread.sleep(5);
                            System.out.println("执行业务逻辑。。。");
                        }catch(Exception e){
                            e.printStackTrace();
                        }finally {
                            count.decrementAndGet();
                        }
                    }
                }
            }).start();
        }
    }
}
