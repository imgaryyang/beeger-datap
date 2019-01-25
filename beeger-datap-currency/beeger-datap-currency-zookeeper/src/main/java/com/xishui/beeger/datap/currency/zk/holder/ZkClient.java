package com.xishui.beeger.datap.currency.zk.holder;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ZkClient {
    CLIENT;
    public static final String DEFAULT_CLIENT = "default.client.key";
    private final static Map<String, CuratorFramework> CLIENTS = new ConcurrentHashMap<>(5);

    public void addClient(String clientKey, CuratorFramework curatorFramework) {
        ZkClient.CLIENTS.putIfAbsent(clientKey, curatorFramework);
    }

    public CuratorFramework defaultClient() {
        return ZkClient.CLIENTS.getOrDefault(DEFAULT_CLIENT, null);
    }

    public void createNode(String path, String value) throws Exception {
        if (null == value) {
            defaultClient().create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).
                    withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(path);
        } else {
            defaultClient().create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).
                    withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(path, value.getBytes());
        }
    }

    public void createNodeExist(String path, String value) throws Exception {
        Stat stat = defaultClient().checkExists().forPath(path);
        if (null != stat) {
            defaultClient().delete().forPath(path);
        }
        if (null == value) {
            defaultClient().create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).
                    withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(path);
        } else {
            defaultClient().create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).
                    withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(path, value.getBytes());
        }
    }

    public boolean checkNodeExist(String path) throws Exception {
        return null != defaultClient().checkExists().forPath(path);
    }

    public void checkNodeExistAndDelete(String path) throws Exception {
        if (checkNodeExist(path)) {
            defaultClient().delete().forPath(path);
        }
    }
}
