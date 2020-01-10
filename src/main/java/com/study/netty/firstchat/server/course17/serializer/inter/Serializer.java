package com.study.netty.firstchat.server.course17.serializer.inter;

import com.study.netty.firstchat.server.course17.serializer.JSONSerializer;

/**
 * 序列化接口
 *
 * @author 卫云鹏
 * @date in 18:46 2020/1/6
 */
public interface Serializer {


    /**
     * 默认的序列化方式
     */
    Serializer DEFAULT_SERIALIZER = new JSONSerializer();

    /**
     * java对象转成byte[]数组
     *
     * @param source 需要被转换的java对象
     * @return
     */
    byte[] java2bytes(Object source);

    /**
     * byte[]数组转换成java对象
     *
     * @param bytes      需要被转换成java对象的byte[]数组
     * @param targetType 需要转换成的java类类类型
     * @param <T>        需要转换成的java类类类型
     * @return
     */
    <T> T bytes2java(byte[] bytes, Class<T> targetType);

    /**
     * 获取当前序列化算法的类型
     * @return
     */
    byte getSerializerType();
}
