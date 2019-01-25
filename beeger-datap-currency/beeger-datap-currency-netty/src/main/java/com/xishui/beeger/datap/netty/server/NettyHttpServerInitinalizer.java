package com.xishui.beeger.datap.netty.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class NettyHttpServerInitinalizer extends ChannelInitializer<Channel> {
    private HttpMessageProcessor httpMessageProcessor;

    public NettyHttpServerInitinalizer(HttpMessageProcessor httpMessageProcessor) {
        this.httpMessageProcessor = httpMessageProcessor;
    }
    @Override
    protected void initChannel(Channel ch) throws Exception {
        // http 的解码器
        ch.pipeline().addLast(new HttpRequestDecoder())
                //http请求包的半包问题，一次接受2m内的数据
                .addLast("http-aggregator", new HttpObjectAggregator(2 * 2014 * 1024))
                .addLast(new HttpResponseEncoder())
                .addLast(new NettyHttpServerHandler(httpMessageProcessor));
    }
}
