package com.study.netty.firstchat.server.course18.server;

import com.study.netty.firstchat.server.course18.commonhandler.IMIdleStateHandler;
import com.study.netty.firstchat.server.course18.commonhandler.PacketCodeCHandler;
import com.study.netty.firstchat.server.course18.server.handler.*;
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
                        //空闲检测
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        //效验使用的协议以及对沾包半包问题的处理handler
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(PacketCodeCHandler.INSTANCE);
                        ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        //效验是否登录的handler，在确认登录后热拔插移除这个handler
                        ch.pipeline().addLast(CheckUserIsLoginHandler.INSTANCE);
                        ch.pipeline().addLast(IMHandler.INSTANCE);
                    }
                }).bind(3308);
    }
}
