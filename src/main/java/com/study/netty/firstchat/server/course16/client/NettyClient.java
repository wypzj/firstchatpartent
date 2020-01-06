package com.study.netty.firstchat.server.course16.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Scanner;

/**
 * @author 卫云鹏
 * @date in 16:27 2020/1/6
 */
public class NettyClient {
    public static void main(String[] args) {
        NioEventLoopGroup clientGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(clientGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {

                    }
                });
        connectServer(bootstrap);
    }

    private static void connectServer(Bootstrap bootstrap) {
        System.out.println("客户端开始连接服务器.....");
        bootstrap.connect("127.0.0.1", 3308).addListener(future -> {
            //判断connect的结果是否成功
            if (future.isSuccess()) {
                //连接成功
                System.out.println("连接成功！启动控制台输入线程");
                ChannelFuture channelFuture = (ChannelFuture) future;
                Channel channel = channelFuture.channel();
                startConsoleThread(channel);
            } else {
                //连接失败,每隔3s会重试连接
                //TODO 一直连接不上会死循环
                System.out.println("连接失败！3s后重试");
                Thread.sleep(3000);
                connectServer(bootstrap);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            //持续监听当前线程
            Scanner scanner = new Scanner(System.in);
            while (!Thread.interrupted()) {
                //TODO 判断当前用户是否已经登录
                // 未登录：提示输入用户名进行登录
                if (false) {
                    System.out.println("请输入用户名进行登录：");
                    String userName = scanner.nextLine();
                } else {
                    //已经登录
                    String input = scanner.nextLine();

                }
            }
        });
    }
}
