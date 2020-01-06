package com.study.netty.firstchat.server.course16.common;

import com.study.netty.firstchat.server.course16.packet.LoginRequestPacket;
import com.study.netty.firstchat.server.course16.packet.inter.AbstractPacket;

/**
 * 指令跟类类型的enum类
 *
 * @author 卫云鹏
 */
public enum CommandsAndClassEnum {
    LOGIN_REQUEST(Commands.LOGIN_REQUEST, LoginRequestPacket.class);


    private byte commandType;

    private Class<AbstractPacket> packetClass;


    private CommandsAndClassEnum(byte commandType, Class<? extends AbstractPacket> packetClass) {}

    public Class<? extends AbstractPacket> getPacketClass(byte commandType){
        for (CommandsAndClassEnum commandsAndClassEnum:CommandsAndClassEnum.values()) {
            if(commandsAndClassEnum.commandType == commandType){
                return commandsAndClassEnum.packetClass;
            }
        }
        //TODO 没有匹配到合适的应该报错
        return null;
    }

}
