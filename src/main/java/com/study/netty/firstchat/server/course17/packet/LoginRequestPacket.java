package com.study.netty.firstchat.server.course17.packet;

import com.study.netty.firstchat.server.course17.common.Commands;
import com.study.netty.firstchat.server.course17.packet.inter.AbstractPacket;
import lombok.*;

/**
 * @author 卫云鹏
 * @date in 19:11 2020/1/6
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequestPacket extends AbstractPacket {

    private String userId;

    private String userName;

    private String pwd;

    @Override
    public byte getCommand() {
        return Commands.LOGIN_REQUEST;
    }
}
