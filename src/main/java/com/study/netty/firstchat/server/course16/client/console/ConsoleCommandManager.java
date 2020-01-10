package com.study.netty.firstchat.server.course16.client.console;

import com.study.netty.firstchat.server.course16.client.console.inter.ConsoleCommandInter;
import com.study.netty.firstchat.server.course16.util.SessionUtil;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 控制台命令控制器
 * @author a small asshole
 */
public class ConsoleCommandManager implements ConsoleCommandInter {
    private Map<String, ConsoleCommandInter> consoleCommandInterMap = new HashMap<>(16);

    public ConsoleCommandManager() {
        consoleCommandInterMap.put("createGroup", new CreateGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        //next方法结束标志 空格 tab键 回车键
        String command = scanner.next();
        ConsoleCommandInter consoleCommand = consoleCommandInterMap.get(command);
        if(consoleCommand != null){
            consoleCommand.exec(scanner, channel);
        }else{
            System.out.println("当前输入的【"+command+"】指令无法识别，请重新输入");
        }
    }
}
