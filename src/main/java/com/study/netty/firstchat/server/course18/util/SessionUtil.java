package com.study.netty.firstchat.server.course18.util;

import com.study.netty.firstchat.server.course18.common.AttributesConstants;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Session工具类
 *
 * @author 卫云鹏
 * @date in 17:57 2020/1/5
 */
@Slf4j
public class SessionUtil {
    /**
     * 在线用户存储的map
     * key：userId
     * value：channel
     */
    private static ConcurrentHashMap<String, Channel> USERS_ONLINE_MAP = new ConcurrentHashMap<>(64);

    public static ConcurrentHashMap<String, Channel> getUsersOnlineMap() {
        return USERS_ONLINE_MAP;
    }

    /**
     * 绑定当前session和channel
     * @param session
     * @param channel
     * @return
     */
    public static boolean bindSession(Session session, Channel channel) {
        String userId = session.getUserId();
        USERS_ONLINE_MAP.put(userId,channel);
        channel.attr(AttributesConstants.SESSION).set(session);
        return true;
    }

    /**
     * 取消绑定
     * @return
     */
    public static boolean unbindSession(Channel channel){
        if(checkIsLogin(channel)){
            Session session = getSession(channel);
            USERS_ONLINE_MAP.remove(session.getUserId());
            channel.attr(AttributesConstants.SESSION).set(null);
            return true;
        }
        log.error("用户尚未登录，不需要取消登录操作");
        return false;
    }

    public static Session getSession(Channel channel){
        return channel.attr(AttributesConstants.SESSION).get();
    }


    /**
     * 效验登录状态
     * @param channel
     * @return
     */
    public static boolean checkIsLogin(Channel channel){
        return channel.attr(AttributesConstants.SESSION).get() != null;
    }

    public static Channel getChannel(String userId){
        return USERS_ONLINE_MAP.get(userId);
    }

}
