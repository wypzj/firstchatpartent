package com.study.netty.firstchat.server.course15;

import com.study.netty.firstchat.server.course15.improvehandler.*;
import com.study.netty.firstchat.server.course15.improvehandler.decoder.CheckProtocolMagicDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Netty服务端程序
 *
 * @author 卫云鹏
 * @date in 12:53 2019/12/13
 */
public class NettyServer {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new CheckProtocolMagicDecoder());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        //效验是否已经登录的逻辑
                        ch.pipeline().addLast(new LoginCheckHandler());
                        ch.pipeline().addLast(new MessageRequestHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        bind(serverBootstrap, 3308);
    }

    /**
     * 动态绑定端口，直到成功为止
     *
     * @param serverBootstrap
     * @param port
     */
    private static void bind(ServerBootstrap serverBootstrap, int port) {
        //绑定端口
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("bind success and port is " + port);
            } else {
                System.out.println("bind fail , 2s later try again");
                Thread.sleep(2000);
                bind(serverBootstrap, port + 1);
            }
        });
    }
}
