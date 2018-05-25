package com.magic.nio.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author 刘俊重
 * @Description 客户端初始化
 * @date 19:07
 */
public class ClientChannelinitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("encoder",new StringEncoder());
        pipeline.addLast("decoder",new StringDecoder());
        pipeline.addLast("handler",new NettyClientHandler());
    }
}
