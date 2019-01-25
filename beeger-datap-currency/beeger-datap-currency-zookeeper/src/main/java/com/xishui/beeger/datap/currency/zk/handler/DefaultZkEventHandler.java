package com.xishui.beeger.datap.currency.zk.handler;

import com.xishui.beeger.datap.currency.zk.ZkNodeData;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultZkEventHandler<T> implements ZkEventHandler<T> {
    private final Logger logger = LoggerFactory.getLogger(DefaultZkEventHandler.class);

    @Override
    public void eventHandler(CuratorFramework curatorFramework, ZkNodeData<T> zkNodeData) {
        logger.info("DefaultEventHandler doing by path:" + zkNodeData.getPath());
    }
}
