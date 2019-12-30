package com.study.netty.firstchat.server.course13.sendmessagedemo.serialize.inter;

import com.study.netty.firstchat.server.course13.sendmessagedemo.serialize.JSONSerializer;

/**
 * 序列化抽象接口
 * @author 卫云鹏
 * @date in 15:39 2019/12/17
 */
public interface Serializer {

    /**
     * 默认序列化算法类型
     */
    byte JSON_SERIALIZER = 1;

    /**
     * 默认序列化算法
     */
    Serializer DEFAULT_ALGORITHM = new JSONSerializer();
    /**
     * 获取序列化的算法
     * @return 序列化算法类型
     */
    byte getSerializerAlgorithm();

    /**
     * java对象转化成二进制字节
     * @param object
     * @return byte[]
     */
    byte[] serialize(Object object);

    /**
     * 二进制字节转成java对象
     * @param target
     * @param bytes
     * @param <T>
     * @return Class<T> target object
     */
    <T> T deserialize(Class<T> target, byte[] bytes);
}
