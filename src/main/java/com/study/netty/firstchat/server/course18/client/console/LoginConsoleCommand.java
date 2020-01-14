package com.study.netty.firstchat.server.course18.client.console;

import com.study.netty.firstchat.server.course18.client.console.inter.ConsoleCommandInter;
import com.study.netty.firstchat.server.course18.packet.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 登录命令控制台指令
 * @author a small asshole
 * @date in 10:39 2020/1/9
 */
public class LoginConsoleCommand implements ConsoleCommandInter {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入用户名进行登录:");
        String userName = scanner.nextLine();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserName(userName);
        channel.writeAndFlush(loginRequestPacket);
        //线程等待两秒再轮询查询响应
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
