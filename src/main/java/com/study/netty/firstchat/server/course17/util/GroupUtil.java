package com.study.netty.firstchat.server.course17.util;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 群组管理的工具类
 *
 * @author a small asshole
 * @date in 15:46 2020/1/10
 */
public class GroupUtil {
    /**
     * 群组信息map
     * key：groupId
     * value：群里用户userId集合
     */
    private static ConcurrentHashMap<String, List<String>> GROUP_MEMBERIDLIST_MAP = new ConcurrentHashMap<>(64);

    /**
     * 群组跟channelgroup的关系map
     * key：groupId
     * value：ChannelGroup
     */
    private static ConcurrentHashMap<String, ChannelGroup> GROUP_CHANNEL_MAP = new ConcurrentHashMap<>(64);

    /**
     * 将groupId和群组用户id绑定
     *
     * @param groupId
     * @param memberIdList
     * @param channelGroup
     * @return
     */
    public static boolean bindGroupMembers(String groupId, List<String> memberIdList, ChannelGroup channelGroup) {
        GROUP_MEMBERIDLIST_MAP.put(groupId, memberIdList);
        GROUP_CHANNEL_MAP.put(groupId, channelGroup);
        return true;
    }

    /**
     * 批量/单个解绑
     *
     * @param groupId
     * @param memberIdList
     * @return
     */
    public static boolean unbindGroupMembers(String groupId, List<String> memberIdList) {
        if (GROUP_MEMBERIDLIST_MAP.containsKey(groupId)) {
            List<String> list = GROUP_MEMBERIDLIST_MAP.get(groupId);
            ChannelGroup channelGroup = GROUP_CHANNEL_MAP.get(groupId);
            list.removeAll(memberIdList);
            for (String userId : memberIdList) {
                //将当前用户的channel从channelGroup剔除
                Channel channel = SessionUtil.getChannel(userId);
                channelGroup.remove(channel);
            }
            return true;
        }
        System.err.println("当前群组不存在");
        return false;
    }

    /**
     * 根据groupId获取channelGroup
     * @param groupId
     * @return
     * @since 1.0
     */
    public static ChannelGroup getChannelGroup(String groupId){
        return GROUP_CHANNEL_MAP.get(groupId);
    }
}
