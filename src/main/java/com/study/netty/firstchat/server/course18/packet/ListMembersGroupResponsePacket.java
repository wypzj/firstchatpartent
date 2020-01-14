package com.study.netty.firstchat.server.course18.packet;


import com.study.netty.firstchat.server.course18.common.Commands;
import com.study.netty.firstchat.server.course18.packet.inter.AbstractPacket;
import com.study.netty.firstchat.server.course18.util.Session;
import lombok.*;

import java.util.List;

/**
 * @author a small asshole
 * @version 1.0
 * @description 获取群组成员响应数据实体类
 * @date in 11:33 2020/1/14
 * @since 1.0
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ListMembersGroupResponsePacket extends AbstractPacket {
    /**
     * 群组成员列表
     */
    private List<Session> groupMemberList;
    @Override
    public byte getCommand() {
        return Commands.LIST_GROUP_MEMBER_RESPONSE;
    }
}
