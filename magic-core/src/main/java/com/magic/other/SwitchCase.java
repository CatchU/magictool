package com.magic.other;

/**
* 利用Switch case 和 break可以做定时补偿任务，设置一个定时器下次还会从当前终止的那一步继续往下走
* @author ljz on 2018/4/9 0009
*/
public class SwitchCase {

    public void testSwitch(int i){
        switch (i){
            case 2:
                System.out.println("开户失败，不停止，继续执行");
            case 1:
                System.out.println("开始开户");
                if(i==2){
                    break;
                }
            case 4:
                System.out.println("更改结算账户失败，继续向下执行");
            case 3:
                System.out.println("开始更改结算账户");
                i=3;
                if(i==4){
                    break;
                }
            case 6:
                System.out.println("调用E签宝失败");
            case 5:
                System.out.println("开始调用E签宝签名");
                i=5;
                if(i==6){
                    break;
                }
            default:
                break;
        }
    }
}
