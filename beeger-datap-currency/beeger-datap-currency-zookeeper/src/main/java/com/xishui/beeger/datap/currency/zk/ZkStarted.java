package com.xishui.beeger.datap.currency.zk;

import com.xishui.beeger.datap.currency.zk.handler.ZkChildNodeChangedHandler;
import org.apache.curator.framework.CuratorFramework;

public enum ZkStarted {
    ZK;

    public void started(ZkProperties zkProperties) throws Exception {
        checkProperties(zkProperties);
        CuratorFramework curatorFramework = new ZkConfiguration(zkProperties).curatorFramework();
        new ZkManager(curatorFramework, new ZkChildNodeChangedHandler()).initZkNode();
    }

    private void checkProperties(ZkProperties zkProperties) throws Exception {


    }
}
