package com.study.netty.firstchat.server.logindemo;

import com.study.netty.firstchat.server.logindemo.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

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
                        //建立连接时，发送消息相关业务逻辑处理
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });
        //监听连接结果，不重试
//        bootstrap.connect("localhost",3307).addListener(future -> {
//            if(future.isSuccess()){
//                System.out.println("conneted success");
//            }else {
//                System.out.println("fail connected");
//            }
//        });
        bootstrap.attr(AttributeKey.newInstance("client-name"), "client1");
        connectServer(bootstrap, "localhost", 3307, RETRY_TIMES);
    }

    private static void connectServer(Bootstrap bootstrap, String host, int part, int maxRetry) {
        bootstrap.connect(host, part).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
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

}
