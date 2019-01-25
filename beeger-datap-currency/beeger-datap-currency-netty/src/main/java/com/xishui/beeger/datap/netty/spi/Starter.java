package com.xishui.beeger.datap.netty.spi;

import com.xishui.beeger.datap.netty.starter.NettyConfig;

public interface Starter {

    void startServer(NettyConfig.NettyServerConfig nettyConfig) throws RuntimeException;

    void startClient(NettyConfig.NettyClientConfig nettyConfig) throws RuntimeException;
}
