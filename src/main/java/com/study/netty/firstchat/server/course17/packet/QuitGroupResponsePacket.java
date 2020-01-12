package com.study.netty.firstchat.server.course17.packet;

import com.study.netty.firstchat.server.course17.common.Commands;
import com.study.netty.firstchat.server.course17.packet.inter.AbstractPacket;

/**
 * @author a small asshole
 * @version 1.0
 * @description 退出群组响应数据实体类
 * @date 2020/1/11
 * @since 1.0
 */
public class QuitGroupResponsePacket extends AbstractPacket {
    @Override
    public byte getCommand() {
        return Commands.QUIT_GROUP_RESPONSE;
    }
}
