package com.xishui.beeger.datap.currency.zk.handler;

import com.xishui.beeger.datap.currency.zk.ZkNodeData;
import org.apache.curator.framework.CuratorFramework;

public interface ZkEventHandler<T> {

    void eventHandler(CuratorFramework curatorFramework, ZkNodeData<T> zkNodeData);
}
