package com.xishui.beeger.datap.currency.zk;

import org.apache.curator.framework.CuratorFramework;

/**
 */
public interface ZkChangedHandler<T> {

    void handler(CuratorFramework curatorFramework, T changedEvent, String parentPath);
}
