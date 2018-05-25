package com.magic.nio.test1;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author 刘俊重
 * @Description 消息服务端
 * @date 10:52
 */
public class WebServer {

    public static void main(String[] args) throws Exception {
        //创建serverSocket通道,绑定ip和端口，设置为非阻塞
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("127.0.0.1",8000));
        ssc.configureBlocking(false);

        //创建选择器,并将channel注册到选择器上，选择器只对accept感兴趣
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        //分配读写缓冲区
        ByteBuffer readBuffer = ByteBuffer.allocate(32);
        ByteBuffer writeBuffer = ByteBuffer.allocate(32);

        writeBuffer.put("received".getBytes());
        writeBuffer.flip();

        while(true){
            //选择器从通道中选择准备就绪的，如果没有，就阻塞等待
            int ready = selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isAcceptable()){
                    //创建新的连接，并将其注册到选择器上，只对read感兴趣
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if(key.isReadable()){
                    //如果已经是读状态，开始读数据
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    readBuffer.clear();
                    socketChannel.read(readBuffer);

                    readBuffer.flip();
                    System.out.println("服务端收到的："+new String(readBuffer.array()));
                    key.interestOps(SelectionKey.OP_WRITE);
                }else if(key.isWritable()){
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    writeBuffer.rewind();
                    socketChannel.write(writeBuffer);
                    System.out.println("服务端发送的："+new String(writeBuffer.array()));
                    key.interestOps(SelectionKey.OP_READ);
                }

            }

        }

    }
}
