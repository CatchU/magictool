package com.magic.nio.test2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author 刘俊重
 * @Description 基于nio实现的服务端
 * @date 14:10
 */
public class NioServer implements Runnable{

    public int id = 100001;
    public int bufferSize = 2048;

    @Override
    public void run() {
        init();
    }

    /**
     * 初始化nio服务器
     */
    public void init(){
        try {
            //创建ServerSocketChannel通道，并绑定ip,设置非阻塞
            ServerSocketChannel scc = ServerSocketChannel.open();
            scc.bind(new InetSocketAddress("127.0.0.1",5000));
            scc.configureBlocking(false);

            //创建选择器，将channel注册到selector
            Selector selector = Selector.open();
            scc.register(selector, SelectionKey.OP_ACCEPT).attach(id++);
            System.out.println("hello server port 5000");
            //选择器开始监听通道
            listener(selector);
        } catch (IOException e) {
            //TODO 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 选择器开始监听通道
     * @param selector
     */
    private void listener(Selector selector) throws IOException {
        while (true){
            //等待准备就绪的channel
            int ready = selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectionKeys.iterator();
            while (it.hasNext()){
                SelectionKey key = it.next();
                if(key==null){
                    continue;
                }
                it.remove();
                //判断是哪个事件
                if(key.isAcceptable()){
                    System.out.println(key.attachment()+"客户端请求连接");
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //将socketChannel注册到选择器上
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ | SelectionKey.OP_WRITE).attach(id++);
                    System.out.println(key.attachment()+"已连接");
                }else if(key.isReadable()){
                    System.out.println(key.attachment()+"读数据");
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(bufferSize);
                    socketChannel.read(readBuffer);

                    System.out.println(key.attachment()+"读取的数据"+new String(readBuffer.array()));
                }else if(key.isWritable()){
                    System.out.println(key.attachment()+"写数据");
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer writeBuffer = ByteBuffer.allocate(bufferSize);

                    String mess = "server send message";
                    writeBuffer.put(mess.getBytes());
                    writeBuffer.flip();
                    socketChannel.write(writeBuffer);
                    System.out.println(key.attachment()+"写的数据"+new String(writeBuffer.array()));
                }else if(key.isConnectable()){
                    System.out.println(key.attachment()+"连接");
                }
            }
        }
    }
}
