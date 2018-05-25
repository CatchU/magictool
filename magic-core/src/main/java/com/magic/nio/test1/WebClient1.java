package com.magic.nio.test1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author 刘俊重
 * @Description 测试客户端
 * @date 11:26
 */
public class WebClient1 {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1",8000));
            socketChannel.configureBlocking(false);

            ByteBuffer readBuffer = ByteBuffer.allocate(32);
            ByteBuffer writeBuffer = ByteBuffer.allocate(32);

            writeBuffer.put("hello".getBytes());
            writeBuffer.flip();
            while (true){
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
