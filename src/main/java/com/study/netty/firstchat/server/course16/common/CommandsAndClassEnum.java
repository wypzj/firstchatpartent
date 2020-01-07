package com.study.netty.firstchat.server.course16.common;

import com.study.netty.firstchat.server.course16.packet.LoginRequestPacket;
import com.study.netty.firstchat.server.course16.packet.inter.AbstractPacket;
import lombok.extern.slf4j.Slf4j;

/**
 * 指令跟类类型的enum类
 *
 * @author 卫云鹏
 */
@Slf4j
public enum CommandsAndClassEnum {
    LOGIN_REQUEST(Commands.LOGIN_REQUEST, LoginRequestPacket.class);

    /**
     * 指令字节
     */
    private byte commandType;

    /**
     * 指令字节对应的协议类类类型
     */
    private Class<? extends AbstractPacket> packetClass;


    private CommandsAndClassEnum(byte commandType, Class<? extends AbstractPacket> packetClass) {
        this.commandType = commandType;
        this.packetClass = packetClass;
    }

    public static Class<? extends AbstractPacket> getPacketClass(byte commandType){
        for (CommandsAndClassEnum commandsAndClassEnum:CommandsAndClassEnum.values()) {
            if(commandsAndClassEnum.commandType == commandType){
                return commandsAndClassEnum.packetClass;
            }
        }
        //TODO 没有匹配到合适的应该报错
        return null;
    }

}
