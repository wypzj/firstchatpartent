package com.study.netty.firstchat.server.course12.sendmessagedemo;

import com.study.netty.firstchat.server.course12.sendmessagedemo.improvehandler.LoginResponseHandler;
import com.study.netty.firstchat.server.course12.sendmessagedemo.improvehandler.PacketDecoder;
import com.study.netty.firstchat.server.course12.sendmessagedemo.improvehandler.PacketEncoder;
import com.study.netty.firstchat.server.course12.sendmessagedemo.pojo.request.MessageRequestPacket;
import com.study.netty.firstchat.server.course12.sendmessagedemo.protocol.PacketCodeC;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * netty客户端程序
 *
 * @author 卫云鹏
 * @date in 20:52 2019/12/15
 */
public class NettyLoginClient {
    public static Integer RETRY_TIMES = 6;

    public static void main(String[] args) {
        NioEventLoopGroup clientGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(clientGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //建立连接时，发送消息相关业务逻辑处理
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        bootstrap.attr(AttributeKey.newInstance("client-name"), "client1");
        connectServer(bootstrap, "localhost", 3307, RETRY_TIMES);
    }

    private static void connectServer(Bootstrap bootstrap, String host, int part, int maxRetry) {
        bootstrap.connect(host, part).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
                ChannelFuture channelFuture = (ChannelFuture) future;
                Channel channel = channelFuture.channel();
                //连接成功后启动控制台线程，可以输入向后台发送消息
                System.out.println("输入消息发送到服务器端：");
                startConsoleThread(channel);
            } else if (maxRetry < 1) {
                System.out.println("连接失败，所有次数都用完了");
            } else {
                int order = RETRY_TIMES - maxRetry + 1;
                System.out.println("连接失败，下次连接是" + order + "次连接");

                //下次连接时间
                long delay = 1 << order;
                //延迟一定时间后，失败重连
                bootstrap.config().group()
                        .schedule(() -> connectServer(bootstrap, host, part, maxRetry - 1)
                                , delay, TimeUnit.SECONDS);
            }
        });
    }

    /**
     *启动控制台输入的线程
     * @param channel A nexus to a network socket or a component which is capable of I/O
     * operations such as read, write, connect, and bind.
     */
    private static void startConsoleThread(Channel channel){
        new Thread(() -> {
            while(!Thread.interrupted()){
                //运行run方法
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                //编码
                MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                messageRequestPacket.setMessage(input);
                ByteBuf encode = PacketCodeC.INSTANCE.encode(messageRequestPacket);
                //发送
                channel.writeAndFlush(encode);
            }
        }).start();
    }
}
