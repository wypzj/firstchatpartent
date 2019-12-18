package com.study.netty.firstchat.server.logindemo.handler;

import com.study.netty.firstchat.server.logindemo.protocol.PacketCodeC;
import com.study.netty.firstchat.server.logindemo.vo.LoginRequestPacket;
import com.study.netty.firstchat.server.logindemo.vo.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author 卫云鹏
 * @date in 11:24 2019/12/16
 */
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        log.info("客户端出数据");
//        //1.获取数据
//        ByteBuf buffer = getByteBuf(ctx);
//        //2.写出数据
//        ctx.writeAndFlush(buffer);
//    }
//
//    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
//        //1.获取netty对二进制的抽象ByteBuf
//        ByteBuf buffer = ctx.alloc().buffer();
//        //2.准备要发生的数据
//        byte[] bytes = "你好，netty".getBytes(Charset.forName("UTF-8"));
//        //3.将数据写入ByteBuf
//        buffer.writeBytes(bytes);
//        return buffer;
//    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("服务器端收到消息");
        ByteBuf byteBuf = (ByteBuf) msg;
        //解码
        Packet loginPacket = PacketCodeC.INSTANCE.decode(byteBuf);
        if(loginPacket instanceof LoginRequestPacket){
            LoginRequestPacket requestPacket = new LoginRequestPacket();
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) loginPacket;
            //检验登录
            if(valid(loginRequestPacket)){
                //登录成功
                requestPacket.setIsSuccess("success");
                requestPacket.setMessage("登录成功");
            }else{
                //登录失败
                requestPacket.setMessage("登录失败");
                requestPacket.setIsSuccess("fail");
            }
            //对消息进行编码
            ByteBuf encode = PacketCodeC.INSTANCE.encode(requestPacket);
            ctx.channel().writeAndFlush(encode);
        }

    }

    private boolean valid(LoginRequestPacket loginRequestPacket){
        //假设所有的登录都是成功的
        return true;
    }
}
