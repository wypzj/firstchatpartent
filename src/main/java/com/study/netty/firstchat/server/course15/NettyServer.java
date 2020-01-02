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
        /*
         * NioEventLoopGroup:从名字看，大致可以理解成，异步网络编程事件组，
         *  可以把它想象成一个团队，至于这个团队是干嘛的，不用管，看名字肯定
         *  能猜出来是处理NioEvent方面问题的。
         */
        //bossGroup：监听端口，专门处理新连接的group
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //workerGroup：这是专门处理每条连接的数据读写的group
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        //引导类，就像一个大的盒子，里面装了不同的团队，都配齐了，盒子就塞满了，就可以寄出去了（服务端就可以启动了）
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
                        ch.pipeline().addLast(new LifeCyCleTestHandler());
                        ch.pipeline().addLast(new CheckProtocolMagicDecoder());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new MessageRequestHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        //绑定固定端口
//        serverBootstrap.bind(1000);
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
