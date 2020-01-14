package com.study.netty.firstchat.server.course18.client.console;

import com.study.netty.firstchat.server.course18.client.console.inter.ConsoleCommandInter;
import com.study.netty.firstchat.server.course18.packet.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 创建群组命令执行类
 * @author a small asshole
 */
public class CreateGroupConsoleCommand implements ConsoleCommandInter {
    /**
     * 群组成员id之间分隔符号
     */
    private static final String SEPARATOR = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("【拉人群聊】，请输入对象的userId，之间用英文','分隔，回车结束：");
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        String membersIds = scanner.next();
        String[] split = membersIds.split(SEPARATOR);
        List<String> list = Arrays.asList(split);
        createGroupRequestPacket.setMemberUserIds(list);
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
