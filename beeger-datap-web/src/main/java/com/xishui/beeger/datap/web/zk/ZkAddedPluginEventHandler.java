package com.xishui.beeger.datap.web.zk;

import com.alibaba.fastjson.JSON;
import com.xishui.beeger.datap.currency.zk.ZkNodeData;
import com.xishui.beeger.datap.currency.zk.handler.ZkEventHandler;
import com.xishui.beeger.datap.plugin.loader.PluginLoaderProvider;
import com.xishui.beeger.datap.web.model.PluginLoaderModel;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ZkAddedPluginEventHandler implements ZkEventHandler<String> {
    private final Logger logger = LoggerFactory.getLogger(ZkAddedPluginEventHandler.class);

    @Override
    public void eventHandler(CuratorFramework curatorFramework, ZkNodeData<String> zkNodeData) {
        String values = zkNodeData.getData();
        if (null == values) {
            logger.error("ZkAddedPluginEvent zkValues is null");
            return;
        }
        try {
            List<PluginLoaderModel> pluginJars = JSON.parseArray(values, PluginLoaderModel.class);
            if (null == pluginJars || pluginJars.size() <= 0) {
                throw new NullPointerException("ZkAddedPluginEvent jars is null.");
            }
            List<String> jarPaths = new ArrayList<>();
            pluginJars.forEach(pluginLoaderModel -> {
                jarPaths.add(pluginLoaderModel.getPluginJar());
            });
            PluginLoaderProvider.newLoader().loader(jarPaths);
        } catch (Exception e) {
            logger.error("ZkAddedPluginEvent Err", e);
        }
    }
}
