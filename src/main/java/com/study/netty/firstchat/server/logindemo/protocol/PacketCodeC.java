package com.study.netty.firstchat.server.logindemo.protocol;

import com.study.netty.firstchat.server.logindemo.serialize.JSONSerializer;
import com.study.netty.firstchat.server.logindemo.serialize.Serializer;
import com.study.netty.firstchat.server.logindemo.serialize.SerializerAlgorithm;
import com.study.netty.firstchat.server.logindemo.vo.LoginRequestPacket;
import com.study.netty.firstchat.server.logindemo.vo.Packet;
import com.study.netty.firstchat.server.logindemo.vo.commands.Command;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;

/**
 * 封装协议，解析协议的类
 *
 * @author 卫云鹏
 * @date in 16:08 2019/12/17
 */
@Slf4j
public class PacketCodeC {

    public static final PacketCodeC INSTANCE = new PacketCodeC();
    /**
     * 协议头魔法值
     */
    private static final int MAGIC_NUMBER = 12345678;

    /**
     * 编码
     *
     * @param packet
     * @return ByteBuf
     */
    public ByteBuf encode(Packet packet) {
        //1.获取ByteBuf
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        //2.序列化对象
        byte[] serialize = Serializer.DEFAULT_ALGORITHM.serialize(packet);
        //2.编写协议
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT_ALGORITHM.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(serialize.length);
        byteBuf.writeBytes(serialize);
        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        //1.获取魔法值
        int magicNumber = byteBuf.readInt();
        //2.获取版本号
        byte version = byteBuf.readByte();
        //3.序列化算法
        byte serializerAlgorithm = byteBuf.readByte();
        //4.指令
        byte command = byteBuf.readByte();
        //5.数据长度
        int dataLength = byteBuf.readInt();
        //6.数据
        byte[] data = new byte[dataLength];
        byteBuf.readBytes(data);

        Class<? extends Packet> packet = getPacketByCommand(command);
        Serializer serializer = getSerializer(serializerAlgorithm);
        assert packet != null;
        assert serializer != null;
        return serializer.deserialize(packet, data);
    }

    private Class<? extends Packet> getPacketByCommand(byte command) {
        if (command == Command.LOGING_COMMAND) {
            return LoginRequestPacket.class;
        }
        return null;
    }

    private Serializer getSerializer(byte serializerAlgorithm) {
        if (serializerAlgorithm == SerializerAlgorithm.JSON) {
            return new JSONSerializer();
        }
        return null;
    }
}
