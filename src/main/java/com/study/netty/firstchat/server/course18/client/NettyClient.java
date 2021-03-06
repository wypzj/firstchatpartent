package com.study.netty.firstchat.server.course18.client;

import com.study.netty.firstchat.server.course18.client.console.ConsoleCommandManager;
import com.study.netty.firstchat.server.course18.client.console.LoginConsoleCommand;
import com.study.netty.firstchat.server.course18.client.handler.*;
import com.study.netty.firstchat.server.course18.commonhandler.PacketCodeCHandler;
import com.study.netty.firstchat.server.course18.server.handler.JoinGroupResponseHandler;
import com.study.netty.firstchat.server.course18.server.handler.Spliter;
import com.study.netty.firstchat.server.course18.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

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
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(PacketCodeCHandler.INSTANCE);
                        ch.pipeline().addLast(new LoginResponseHandler());
                        //发送心跳包
                        ch.pipeline().addLast(HeartBeatTimerHandler.INSTANCE);
                        ch.pipeline().addLast(new CreateGroupResponseHandler());
                        ch.pipeline().addLast(new JoinGroupResponseHandler());
                        ch.pipeline().addLast(new ListMembersGroupResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new TestOutBoundHandler());
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
                //TODO 一直连接不上会死循环，需要优化
                System.out.println("连接失败！3s后重试");
                Thread.sleep(3000);
                connectServer(bootstrap);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        ConsoleCommandManager commandManager = new ConsoleCommandManager();
        new Thread(() -> {
            //持续监听当前线程
            Scanner scanner = new Scanner(System.in);
            while (!Thread.interrupted()) {
                // 未登录：提示输入用户名进行登录
                if (!SessionUtil.checkIsLogin(channel)) {
                    loginConsoleCommand.exec(scanner,channel);
                } else {
                    //已经登录
                    commandManager.exec(scanner,channel);
                }
            }
        }).start();
    }
}
