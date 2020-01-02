package com.study.netty.firstchat.server.courese14.constants;

/**
 * @author 卫云鹏
 * @date in 15:34 2019/12/17
 */
public interface Command {
    /**
     * 登录请求指令
     */
    Byte LOGING_COMMAND = 1;
    /**
     * 登录回应指令
     */
    Byte LOGINGOUT_COMMAND = 2;

    /**
     * 发送消息请求指令
     */
    Byte MESSAGEREQUEST_COMMAND = 3;
    /**
     * 发送消息回应指令
     */
    Byte MESSAGERESPONSE_COMMAND = 4;

}
