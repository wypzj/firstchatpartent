package com.study.netty.firstchat.server.course18.client.console;

import com.study.netty.firstchat.server.course18.client.console.inter.ConsoleCommandInter;
import com.study.netty.firstchat.server.course18.packet.ListMembersGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author a small asshole
 * @version 1.0
 * @description 获取群成员列表命令执行类
 * @date in 9:58 2020/1/14
 * @since 1.0
 */
public class ListGroupMemberConsoleCommand implements ConsoleCommandInter {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入groupId，获取群成员列表：");
        String groupId = scanner.next();
        //构建请求
        ListMembersGroupRequestPacket requestPacket = new ListMembersGroupRequestPacket();
        requestPacket.setGroupId(groupId);

        //写出
        channel.writeAndFlush(requestPacket);
    }
}
