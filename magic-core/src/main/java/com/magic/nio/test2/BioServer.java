package com.magic.nio.test2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 刘俊重
 * @Description
 * @date 15:32
 */
public class BioServer implements Runnable{
    @Override
    public void run() {
        System.out.println("Hello Server!!");
        try {
            //创建一个ServerSocket在端口4700监听客户请求
            ServerSocket server = new ServerSocket(4700);
            // 使用accept()阻塞等待客户请求，有客户请求到来则产生一个Socket对象，并继续执行
            Socket socket = server.accept();
            String line;
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Client:" + is.readLine());
            PrintWriter os = new PrintWriter(socket.getOutputStream());
            // 在标准输出上打印从客户端读入的字符串
            line = "hello";
            os.println(line);
            // 向客户端输出该字符串
            os.flush();
            is.close(); // 关闭Socket输入流
            socket.close(); // 关闭Socket
            server.close(); // 关闭ServerSocket
        } catch (Exception e) {
            System.out.println("Error." + e);
            // 出错，打印出错信息
        }

    }
}
