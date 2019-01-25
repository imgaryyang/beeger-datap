package com.xishui.beeger.datap.netty.spi;

import io.netty.channel.Channel;

/**
 * 承接netty服务的消息统一处理器
 * @param <T>
 */
public interface MessageProcessor<T> {

    void processor(T message, Channel channel);
}
