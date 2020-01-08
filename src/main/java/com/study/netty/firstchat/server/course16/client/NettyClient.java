package com.study.netty.firstchat.server.course16.client;

import com.study.netty.firstchat.server.course16.client.handler.LoginResponseHandler;
import com.study.netty.firstchat.server.course16.client.handler.MessageResponseHandler;
import com.study.netty.firstchat.server.course16.commonhandler.PacketDecoder;
import com.study.netty.firstchat.server.course16.commonhandler.PacketEncoder;
import com.study.netty.firstchat.server.course16.packet.LoginRequestPacket;
import com.study.netty.firstchat.server.course16.server.handler.Spliter;
import com.study.netty.firstchat.server.course16.util.SessionUtil;
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
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
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
        new Thread(() -> {
            //持续监听当前线程
            Scanner scanner = new Scanner(System.in);
            while (!Thread.interrupted()) {
                // 未登录：提示输入用户名进行登录
                if (!SessionUtil.checkIsLogin(channel)) {
                    System.out.println("请输入用户名进行登录：");
                    String userName = scanner.nextLine();
                    LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
                    loginRequestPacket.setUserName(userName);
                    channel.writeAndFlush(loginRequestPacket);
                    //线程等待两秒再轮询查询响应
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    //已经登录
                    System.out.println("登录成功！可以发送消息了：");
                    String input = scanner.nextLine();

                }
            }
        }).start();
    }
}
