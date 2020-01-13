package com.study.netty.firstchat.server.course17.packet;

import com.study.netty.firstchat.server.course17.common.Commands;
import com.study.netty.firstchat.server.course17.packet.inter.AbstractPacket;
import lombok.*;

/**
 * @author a small asshole
 * @version 1.0
 * @description 退出群组响应数据实体类
 * @date 2020/1/11
 * @since 1.0
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuitGroupResponsePacket extends AbstractPacket {
    private String groupId;
    /**
     * 是否退出成功
     */
    private boolean tip;

    @Override
    public byte getCommand() {
        return Commands.QUIT_GROUP_RESPONSE;
    }
}
