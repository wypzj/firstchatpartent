package com.study.netty.firstchat.server.course15;

import com.study.netty.firstchat.server.course15.improvehandler.LoginResponseHandler;
import com.study.netty.firstchat.server.course15.improvehandler.MessageResponseHandler;
import com.study.netty.firstchat.server.course15.improvehandler.PacketDecoder;
import com.study.netty.firstchat.server.course15.improvehandler.PacketEncoder;
import com.study.netty.firstchat.server.course15.pojo.request.LoginRequestPacket;
import com.study.netty.firstchat.server.course15.pojo.request.MessageRequestPacket;
import com.study.netty.firstchat.server.course15.protocol.PacketCodeC;
import com.study.netty.firstchat.server.course15.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.AttributeKey;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * netty客户端程序
 *
 * @author 卫云鹏
 * @date in 20:52 2019/12/15
 */
public class NettyClient {
    public static Integer RETRY_TIMES = 6;

    public static void main(String[] args) {
        NioEventLoopGroup clientGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(clientGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        bootstrap.attr(AttributeKey.newInstance("client-name"), "client1");
        connectServer(bootstrap, "localhost", 3308, RETRY_TIMES);
    }

    private static void connectServer(Bootstrap bootstrap, String host, int part, int maxRetry) {
        bootstrap.connect(host, part).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
                ChannelFuture channelFuture = (ChannelFuture) future;
                Channel channel = channelFuture.channel();
                //连接成功后启动控制台线程，可以输入向后台发送消息
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
     * 启动控制台输入的线程
     *
     * @param channel A nexus to a network socket or a component which is capable of I/O
     *                operations such as read, write, connect, and bind.
     */
    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                //运行run方法
                Scanner scanner = new Scanner(System.in);
                if(!SessionUtil.checkIsLogin(channel)){
                    //未登录
                    System.out.println("请输入用户名进行登录：");
                    String userName = scanner.nextLine();
                    LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
                    loginRequestPacket.setUsername(userName);
                    System.out.println("客户端发送登录请求.....");
                    channel.writeAndFlush(loginRequestPacket);
                    //等待登录响应
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    String input = scanner.nextLine();
                    //编码
                    MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                    String[] inputStr = input.split(" ",0);
                    messageRequestPacket.setToUserId(inputStr[0]);
                    messageRequestPacket.setMessage(inputStr[1]);
                    //发送
                    ByteBuf encode = PacketCodeC.INSTANCE.encode(messageRequestPacket);
                    channel.writeAndFlush(encode);
                }
            }
        }).start();
    }
}
