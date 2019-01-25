package com.xishui.beeger.datap.currency.zk;

import com.xishui.beeger.datap.currency.zk.holder.ZkClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class ZkConfiguration {
    private final Logger logger = LoggerFactory.getLogger(ZkConfiguration.class);
    private ZkProperties zkProperties;

    public ZkConfiguration(ZkProperties zkProperties) {
        this.zkProperties = zkProperties;
    }

    public CuratorFramework curatorFramework() {
        //可以加入权限部分
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(zkProperties.getConnect()).connectionTimeoutMs(zkProperties.getConnectionTimeOut())
                .sessionTimeoutMs(zkProperties.getSessionTimeOut()).retryPolicy(new RetryOneTime(2000)).build();
        curatorFramework.start();
        ZkClient.CLIENT.addClient(zkProperties.getClientKey(), curatorFramework);
        logger.info("Zookeeper Init Framework Completed");
        return curatorFramework;
    }
}
