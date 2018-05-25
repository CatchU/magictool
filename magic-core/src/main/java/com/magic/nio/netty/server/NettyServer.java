package com.magic.nio.netty.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author 刘俊重
 * @Description netty实现的服务器
 * @date 18:56
 */
public class NettyServer {

    private int port;

    public NettyServer(int port) {
        this.port = port;
    }

    public void startUpServer() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap sbs = new ServerBootstrap();
        sbs.group(group).
                channel(NioServerSocketChannel.class).
                childHandler(new ServerChannelInitializer());

        ChannelFuture future = sbs.bind(port).sync();
        future.channel().closeFuture().sync();
    }

    public static void main(String[] args) throws Exception {
        NettyServer server = new NettyServer( 6000);
        server.startUpServer();
    }
}
