package com.study.netty.firstchat.server.course16.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author 卫云鹏
 * @date in 16:27 2020/1/6
 */
public class NettyServer {
    public static void main(String[] args) {
        NioEventLoopGroup threadJoinListener = new NioEventLoopGroup();
        NioEventLoopGroup threadDataTransportListener = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap
                .group(threadJoinListener, threadDataTransportListener)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {

                    }
                }).bind(3308);
    }
}
