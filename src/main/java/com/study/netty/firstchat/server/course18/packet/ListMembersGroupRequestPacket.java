package com.study.netty.firstchat.server.course18.packet;

import com.study.netty.firstchat.server.course18.common.Commands;
import com.study.netty.firstchat.server.course18.packet.inter.AbstractPacket;
import lombok.*;

/**
 * @author a small asshole
 * @version 1.0
 * @description 获取群成员的数据请求实体类
 * @date in 10:02 2020/1/14
 * @since 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ListMembersGroupRequestPacket extends AbstractPacket {
    private String groupId;
    @Override
    public byte getCommand() {
        return Commands.LIST_GROUP_MEMBER_REQUEST;
    }
}
