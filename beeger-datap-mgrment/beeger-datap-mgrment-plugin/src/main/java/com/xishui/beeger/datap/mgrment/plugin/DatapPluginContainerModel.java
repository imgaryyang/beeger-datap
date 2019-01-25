package com.xishui.beeger.datap.mgrment.plugin;

import com.xishui.beeger.datap.container.ContainerModel;
import com.xishui.beeger.datap.netty.starter.NettyConfig;
import com.xishui.beeger.datap.netty.starter.NettyStarter;

/**
 * plugin 容器的启动
 */
public class DatapPluginContainerModel implements ContainerModel {
    @Override
    public void starter() throws RuntimeException {
        System.out.println("Plugin Container Started.");
        NettyStarter.HTTP.startServer(NettyConfig.createNettyServerConfig().exportPort(3000)
                .messageProcessor(new PluginMrgMessageProcessor()));
    }

    @Override
    public void stop() throws RuntimeException {

    }
}
