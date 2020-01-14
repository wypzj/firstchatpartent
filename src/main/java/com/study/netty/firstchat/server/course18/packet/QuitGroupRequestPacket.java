package com.study.netty.firstchat.server.course18.packet;

import com.study.netty.firstchat.server.course18.common.Commands;
import com.study.netty.firstchat.server.course18.packet.inter.AbstractPacket;
import lombok.*;

/**
 * @author a small asshole
 * @version 1.0
 * @description 退出群聊请求数据实体类
 * @date 2020/1/11
 * @since 1.0
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuitGroupRequestPacket extends AbstractPacket {
    /**
     * 群组id
     */
    private String groupId;
    @Override
    public byte getCommand() {
        return Commands.QUIT_GROUP_REQUEST;
    }
}
