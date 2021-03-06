package com.magic.nio.test2;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author 刘俊重
 * @Description
 * @date 15:35
 */
public class TestBioServer {

    @Test
    public void startUpBioServer(){
        Thread server = new Thread(new BioServer());
        server.start();
        while(true){
        }
    }

    @Test
    public void startUpClient() throws Exception{
        Socket socket=new Socket("127.0.0.1",4700);//BIO 阻塞
        System.out.println("连接成功");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //下面这种写法，不用关闭客户端，服务器端也是可以收到的
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        printWriter.println("hi");
        printWriter.flush();
        byte[] buf = new byte[2048];
        System.out.println("准备读取数据~~");
        while(true){
            try {
                //两种读取数据方式
                int count = socket.getInputStream().read(buf);        //会阻塞
                System.out.println("方式一： 读取数据" + new String(buf) + " count = " + count);
                Thread.sleep(1*1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


}
