package com.xishui.beeger.datap.netty.spi;

import java.util.Map;

/**
 * 承接分发器的消息具体处理器
 * @param <P>
 * @param <R>
 */
public interface MessageExecution<P, R> {

    R execution(P param,Map<String,Object> extendParams);
}
