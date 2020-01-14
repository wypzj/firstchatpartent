package com.study.netty.firstchat.server.course18.protocol;

import com.study.netty.firstchat.server.course18.common.CommandsAndClassEnum;
import com.study.netty.firstchat.server.course18.common.SerializeConstants;
import com.study.netty.firstchat.server.course18.packet.inter.AbstractPacket;
import com.study.netty.firstchat.server.course18.serializer.JSONSerializer;
import com.study.netty.firstchat.server.course18.serializer.inter.Serializer;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;

/**
 * 协议解析/封装工具类
 * @author 卫云鹏
 * @date in 19:38 2020/1/6
 */
@Slf4j
public class ProtocolCodec {
    public static final int MAGIC_NUMBER = 0X123456;

    public static ProtocolCodec INSTANCE = new ProtocolCodec();
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
        //数据
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    /**
     * 解码
     * @param byteBuf
     * @return
     */
    public AbstractPacket decode(ByteBuf byteBuf){
        //魔数
        int magicNum = byteBuf.readInt();
        //协议版本
        byte version = byteBuf.readByte();
        //序列化算法
        byte serializerNum = byteBuf.readByte();
        //指令
        byte command = byteBuf.readByte();

        //数据长度
        int datalLength = byteBuf.readInt();
        //数据
        byte[] bytes = new byte[datalLength];
        byteBuf.readBytes(bytes);
        //协议类类型
        Class<? extends AbstractPacket> packetClass = CommandsAndClassEnum.getPacketClass(command);
        //序列化算法
        Serializer serializer = getSerializer(serializerNum);
        return serializer.bytes2java(bytes, packetClass);
    }

    private Serializer getSerializer(byte serializerNum){
        if(serializerNum == SerializeConstants.JSON_SERIALIZER){
            return new JSONSerializer();
        }
        log.error("没有匹配到相应的序列化器");
        return null;
    }
}
