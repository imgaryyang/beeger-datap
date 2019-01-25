package com.xishui.beeger.datap.netty.server;

import com.xishui.beeger.datap.netty.spi.Starter;
import com.xishui.beeger.datap.netty.starter.NettyConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyHttpStarter implements Starter {
    @Override
    public void startServer(NettyConfig.NettyServerConfig nettyConfig) throws RuntimeException {
        if (null == nettyConfig) {
            throw new RuntimeException("netty server config is null.");
        }
        if (nettyConfig.getExportPort() < 1000) {
            throw new RuntimeException("netty export port less than 1000");
        }
        if (null == nettyConfig.getMessageProcessor() || !(nettyConfig.getMessageProcessor() instanceof HttpMessageProcessor)) {
            throw new RuntimeException("HttpMessageProcessor is null or non-instanceof HttpMessageProcessor");
        }
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup works = new NioEventLoopGroup(10);
        HttpMessageProcessor processor = (HttpMessageProcessor) nettyConfig.getMessageProcessor();
        try {
            ServerBootstrap boot = new ServerBootstrap();
            boot.group(boss, works).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new NettyHttpServerInitinalizer(processor));
            ChannelFuture future = boot.bind(nettyConfig.getExportPort()).sync();
            future.channel().closeFuture().syncUninterruptibly();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            works.shutdownGracefully();
        }

    }

    @Override
    public void startClient(NettyConfig.NettyClientConfig nettyConfig) throws RuntimeException {

    }
}
