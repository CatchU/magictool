package com.magic.highconcurrent.currentlimit;

import java.util.concurrent.Semaphore;

/**
 * @author 刘俊重
 * @Description
 * @date 14:07
 */
public class CurrentLimiterBySempahore {

    private static Semaphore semphore = new Semaphore(5);

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(semphore.getQueueLength()>30){
                        System.out.println("排队的太多。。");
                    }
                    try{
                        semphore.acquire();
                        Thread.sleep(50);
                        System.out.println("执行核心业务逻辑。。。");
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        semphore.release();
                    }
                }
            }).start();
        }

    }
}
