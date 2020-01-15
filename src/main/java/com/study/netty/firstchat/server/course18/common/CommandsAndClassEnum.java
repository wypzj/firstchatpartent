package com.study.netty.firstchat.server.course18.common;

import com.study.netty.firstchat.server.course18.packet.*;
import com.study.netty.firstchat.server.course18.packet.inter.AbstractPacket;
import lombok.extern.slf4j.Slf4j;

/**
 * 指令跟类类型的enum类
 *
 * @author 卫云鹏
 */
@Slf4j
public enum CommandsAndClassEnum {
    /**
     * 登录请求/响应-指令和类类型对应关系
     */
    LOGIN_REQUEST(Commands.LOGIN_REQUEST, LoginRequestPacket.class),
    LOGIN_RESPONSE(Commands.LOGIN_RESPONSE, LoginResponsePacket.class),
    /**
     * 发送消息-指令和消息类类型对应关系
     */
    MESSAGE_REQUEST(Commands.MESSAGE_REQUEST, MessageRequestPacket.class),
    MESSAGE_RESPONSE(Commands.MESSAGE_RESPONSE, MessageResponsePacket.class),
    /**
     * 创建群聊请求、响应
     */
    CREATEGROUP_REQUEST(Commands.CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class),
    CREATEGROUP_RESPONSE(Commands.CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class),
    /**
     * 加入群聊请求、响应
     */
    JOINGROUP_REQUEST(Commands.JOIN_CROUP_REQUEST, JoinGroupRequestPacket.class),
    JOINGROUP_RESPONSE(Commands.JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class),
    /**
     * 退出群聊请求、响应
     */
    QUITGROUP_REQUEST(Commands.QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class),
    QUITGROUP_RESPONSE(Commands.QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class),
    /**
     * 查询群成员请求、响应
     */
    LISTGROUPMEMBER_REQUEST(Commands.LIST_GROUP_MEMBER_REQUEST, ListMembersGroupRequestPacket.class),
    LISTGROUPMEMBER_RESPONSE(Commands.LIST_GROUP_MEMBER_RESPONSE, ListMembersGroupResponsePacket.class),
    /**
     * 客户端心跳请求
     */
    HEART_BEAT_REQUEST(Commands.HEART_BEAT_REQUEST, HeartBeatRequestPacket.class);


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

    public static Class<? extends AbstractPacket> getPacketClass(byte commandType) {
        for (CommandsAndClassEnum commandsAndClassEnum : CommandsAndClassEnum.values()) {
            if (commandsAndClassEnum.commandType == commandType) {
                return commandsAndClassEnum.packetClass;
            }
        }
        log.error("没有匹配到响应的协议类类类型");
        return null;
    }

}
