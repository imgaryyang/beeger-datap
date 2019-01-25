package com.xishui.beeger.datap.netty.spi;

/**
 *
 * @param <P>
 * @param <R>
 */
public interface MessageDispather<P, R> {

    MessageExecution<P, R> dispather(P param);
}
