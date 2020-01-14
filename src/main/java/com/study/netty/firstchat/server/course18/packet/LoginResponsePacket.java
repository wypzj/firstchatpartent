package com.study.netty.firstchat.server.course18.packet;

import com.study.netty.firstchat.server.course18.common.Commands;
import com.study.netty.firstchat.server.course18.packet.inter.AbstractPacket;
import lombok.*;

/**
 * @author 卫云鹏
 * @date in 19:20 2020/1/6
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginResponsePacket extends AbstractPacket {

    private String userId;

    private String userName;

    /**
     * 登录成功的标签
     * 登录成功：true
     * 登录失败：false
     */
    private boolean loginTig;

    @Override
    public byte getCommand() {
        return Commands.LOGIN_RESPONSE;
    }
}
