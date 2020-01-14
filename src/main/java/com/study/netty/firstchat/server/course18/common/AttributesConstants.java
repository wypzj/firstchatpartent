package com.study.netty.firstchat.server.course18.common;

import com.study.netty.firstchat.server.course18.util.Session;
import io.netty.util.AttributeKey;

/**
 * channel属性常量类
 *
 * @author 卫云鹏
 * @date in 17:56 2020/1/6
 */
public class AttributesConstants {
    /**
     * 登录属性
     */
    public static final AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    /**
     * Session属性
     */
    public static final AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
