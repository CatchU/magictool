package com.magic.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
*   使用多线程完成注册
* @author ljz on 2018/4/9 0009
*/
public class RegisterHelper {

    private ThreadPoolExecutor registerPool = new ThreadPoolExecutor(1,5,
            0L, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(10));

    /**
     * 提供给外部的调用方法
    * @author ljz on 2018/4/9 0009
    * @param userId
    * @return
    */
    public void registerAsync(Long userId){
        try{
            //调用多线程
            RegisterTask registerTask = new RegisterTask(userId);
            registerPool.execute(registerTask);
        }catch(Exception e){
            //抛出错误日志
        }
    }

    /**
    * 多线程
    * @author ljz on 2018/4/9 0009
    */
    class RegisterTask implements Runnable{

        private Long userId;

        public RegisterTask(Long userId) {
            this.userId = userId;
        }

        public void run() {
            pushRegisterStatus(userId);
        }

        /**
         * 推动用户注册状态运行
        * @author ljz on 2018/4/9 0009
        * @param userId
        * @return
        */
        private void pushRegisterStatus(Long userId){
            System.out.println("回显供应商信息。。。");
            System.out.println("查询是否开户。。。");
            System.out.println("调用银行接口开户。。。");
            System.out.println("调用E签宝签名。。。");
            System.out.println("开户成功。。。");
            return ;
        }
    }

}
