package com.magic.nio.test2;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author 刘俊重
 * @Description 测试nio服务器
 * @date 14:38
 */
public class TestNioServer {

    /**
     * 启动nio服务器
     */
    @Test
    public void startUpNioServer(){
        NioServer nioServer = new NioServer();
        Thread nioThread = new Thread(nioServer);
        nioThread.start();
        while (true){
        }
    }

    /**
     * 启动测试客户端
     */
    @Test
    public void startUpClient(){
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1",5000));
            socketChannel.configureBlocking(false);

            ByteBuffer readBuffer = ByteBuffer.allocate(32);
            ByteBuffer writeBuffer = ByteBuffer.allocate(32);

            writeBuffer.put("hello".getBytes());
            while (true){
                writeBuffer.flip();
                writeBuffer.rewind();
                socketChannel.write(writeBuffer);
                System.out.println("客户端1发送的："+new String(writeBuffer.array()));

                readBuffer.clear();
                socketChannel.read(readBuffer);
                readBuffer.flip();
                System.out.println("客户端1接收到的："+new String(readBuffer.array()));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
