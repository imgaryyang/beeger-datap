package com.xishui.beeger.datap.netty.starter;

import com.xishui.beeger.datap.netty.server.NettyHttpStarter;

public enum NettyStarter {
    SOCKET {
        @Override
        public void startServer(NettyConfig.NettyServerConfig nettyConfig) {

        }

        @Override
        public void startClient(NettyConfig.NettyClientConfig nettyConfig) {

        }
    },
    HTTP {
        @Override
        public void startServer(NettyConfig.NettyServerConfig nettyConfig) {
            new NettyHttpStarter().startServer(nettyConfig);
        }

        @Override
        public void startClient(NettyConfig.NettyClientConfig nettyConfig) {
            new NettyHttpStarter().startClient(nettyConfig);
        }
    };

    public abstract void startServer(NettyConfig.NettyServerConfig nettyConfig);

    public abstract void startClient(NettyConfig.NettyClientConfig nettyConfig);
}
