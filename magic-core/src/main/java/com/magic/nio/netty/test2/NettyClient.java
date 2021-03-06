package com.magic.nio.netty.test2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

/**
 * @author junzhongliu
 * @date 2018/11/28 13:18
 */
public class NettyClient {

    public static void main(String[] args) throws Exception{
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new StringEncoder());
            }
        });

        Channel channel = bootstrap
                .connect("127.0.0.1", 9000)
                .channel();

        while (true){
            channel.writeAndFlush(new Date()+":hello word");
            Thread.sleep(1000);
        }
    }
}
