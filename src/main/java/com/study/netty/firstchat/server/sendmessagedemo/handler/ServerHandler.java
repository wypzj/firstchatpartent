package com.study.netty.firstchat.server.sendmessagedemo.handler;

import com.study.netty.firstchat.server.sendmessagedemo.constants.Attributes;
import com.study.netty.firstchat.server.sendmessagedemo.pojo.request.MessageRequestPacket;
import com.study.netty.firstchat.server.sendmessagedemo.pojo.response.MessageResponsePacket;
import com.study.netty.firstchat.server.sendmessagedemo.protocol.PacketCodeC;
import com.study.netty.firstchat.server.sendmessagedemo.pojo.request.LoginRequestPacket;
import com.study.netty.firstchat.server.sendmessagedemo.pojo.abstractpojo.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

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
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginRequestPacket) {
            //对消息进行编码
            ByteBuf encode = loginRequestPacketHandler(packet,ctx);
            ctx.channel().writeAndFlush(encode);
        }else if(packet instanceof MessageRequestPacket){
            ByteBuf encode = messageRequestPacketHandler(packet, ctx);
            ctx.writeAndFlush(encode);
        }
    }

    /**
     * 对登录协议请求进行解析构造响应
     * @param loginPacket
     * @param ctx
     * @return
     */
    private ByteBuf loginRequestPacketHandler(Packet loginPacket, ChannelHandlerContext ctx) {
        LoginRequestPacket requestPacket = new LoginRequestPacket();
        LoginRequestPacket loginRequestPacket = (LoginRequestPacket) loginPacket;
        //检验登录
        if (valid(loginRequestPacket)) {
            //登录成功
            requestPacket.setTig(true);
            requestPacket.setMessage("登录成功");
            //登录成功后设置已经登录的标识
            ctx.channel().attr(Attributes.LOGIN).set(true);
        } else {
            //登录失败
            requestPacket.setMessage("登录失败");
            requestPacket.setTig(false);
        }
        //对消息进行编码
        return PacketCodeC.INSTANCE.encode(requestPacket);
    }

    private ByteBuf messageRequestPacketHandler(Packet packet, ChannelHandlerContext ctx){
        MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
        System.out.println("客户端："+messageRequestPacket.getMessage());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());
        //编码
        return PacketCodeC.INSTANCE.encode(messageResponsePacket);
    }
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        //假设所有的登录都是成功的
        return true;
    }
}
