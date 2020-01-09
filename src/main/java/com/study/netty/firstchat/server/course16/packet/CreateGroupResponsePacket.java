package com.study.netty.firstchat.server.course16.packet;

import com.study.netty.firstchat.server.course16.common.Commands;
import com.study.netty.firstchat.server.course16.packet.inter.AbstractPacket;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建分组响应
 * @author a small asshole
 * @date in 14:19 2020/1/9
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateGroupResponsePacket extends AbstractPacket {
    /**
     * 群组成员name
     */
    private List<String> memberNameList;

    private boolean createSuccess;

    /**
     * 群聊组id
     */
    private String groupId;

    @Override
    public byte getCommand() {
        return Commands.CREATE_GROUP_RESPONSE;
    }
}
