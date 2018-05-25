package com.magic.nio.netty.client;

import com.magic.nio.netty.server.NettyServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author 刘俊重
 * @Description netty实现的客户端
 * @date 19:14
 */
public class NettyClient {

    private String ipAddr;

    private int port;

    public NettyClient(String ipAddr, int port) {
        this.ipAddr = ipAddr;
        this.port = port;
    }

    public void startUpClient() throws Exception{
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bs = new Bootstrap();
        bs.group(group).
                channel(NioSocketChannel.class).
                handler(new ClientChannelinitializer());

        Channel channel = bs.connect(ipAddr, port).sync().channel();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for(;;){
            String msg = reader.readLine();
            if(msg==null){
                continue;
            }
            channel.writeAndFlush(msg+ "\r\n");
        }
    }

    public static void main(String[] args) throws Exception{
        NettyClient client = new NettyClient("127.0.0.1",6000);
        client.startUpClient();
    }
}
