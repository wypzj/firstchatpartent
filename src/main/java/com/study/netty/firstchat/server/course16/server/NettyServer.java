package com.study.netty.firstchat.server.course16.server;

import com.study.netty.firstchat.server.course16.commonhandler.PacketDecoder;
import com.study.netty.firstchat.server.course16.commonhandler.PacketEncoder;
import com.study.netty.firstchat.server.course16.server.handler.CheckUserIsLoginHandler;
import com.study.netty.firstchat.server.course16.server.handler.Spliter;
import com.study.netty.firstchat.server.course16.server.handler.LoginRequestHandler;
import com.study.netty.firstchat.server.course16.server.handler.MessageHandler;
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
                        //效验使用的协议以及对沾包半包问题的处理handler
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        //效验是否登录的handler，在确认登录后热拔插移除这个handler
                        ch.pipeline().addLast(new CheckUserIsLoginHandler());
                        ch.pipeline().addLast(new MessageHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                }).bind(3308);
    }
}
