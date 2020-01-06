package com.study.netty.firstchat.server.course16.protocol;

import com.study.netty.firstchat.server.course16.packet.inter.AbstractPacket;
import com.study.netty.firstchat.server.course16.serializer.inter.Serializer;
import io.netty.buffer.ByteBuf;

/**
 * 协议解析/封装工具类
 * @author 卫云鹏
 * @date in 19:38 2020/1/6
 */
public class ProtocolCodec {
    public static final int MAGIC_NUMBER = 0X123456;
    /**
     * 编码
     * @param byteBuf
     * @param packet
     * @return
     */
    public ByteBuf encode(ByteBuf byteBuf, AbstractPacket packet){
        byte[] bytes = Serializer.DEFAULT_SERIALIZER.java2bytes(packet);
        /**
         * -魔数（4字节）-\-版本号（1字节）-\-序列化算法（1字节）-|-指令（1字节）-|-数据长度（4字节）-|-数据（N字节）
         */
        //设置协议魔数
        byteBuf.writeInt(MAGIC_NUMBER);
        //协议版本
        byteBuf.writeByte(AbstractPacket.VERSION);
        //序列化算法
        byteBuf.writeByte(Serializer.DEFAULT_SERIALIZER.getSerializerType());
        //指令
        byteBuf.writeByte(packet.getCommand());
        //数据长度
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    /**
     * 解码
     * @param byteBuf
     * @return
     */
    public AbstractPacket decode(ByteBuf byteBuf){
        //TODO 2020/1/6 20:25 今晚完成解码工作
        return null;
    }
}
