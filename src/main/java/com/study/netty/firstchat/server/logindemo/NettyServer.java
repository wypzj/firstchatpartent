package com.study.netty.firstchat.server.logindemo;

import com.study.netty.firstchat.server.logindemo.handler.ServerHandler;
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
                //指定 绑定监听新建连接的group 和 监听数据传输的group
                .group(bossGroup, workerGroup)
                //指定netty使用io模型（阻塞型），nio模型（异步）
                .channel(NioServerSocketChannel.class)
                //TODO 不理解，后续补充上
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ServerHandler());
                    }
                });
        //绑定固定端口
//        serverBootstrap.bind(1000);
        bind(serverBootstrap, 3306);
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
