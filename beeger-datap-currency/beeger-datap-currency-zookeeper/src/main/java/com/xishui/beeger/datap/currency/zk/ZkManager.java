package com.xishui.beeger.datap.currency.zk;

import com.xishui.beeger.datap.currency.zk.handler.ZkChildNodeChangedHandler;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 1.创建节点
 * 2.增加监听器
 * 3.删除节点
 */
public class ZkManager {
    private final Logger logger = LoggerFactory.getLogger(ZkManager.class);
    private CuratorFramework curatorFramework;
    private ZkChildNodeChangedHandler zkChildNodeChangedHandler;

    public ZkManager(CuratorFramework curatorFramework, ZkChildNodeChangedHandler zkChildNodeChangedHandler) {
        this.curatorFramework = curatorFramework;
        this.zkChildNodeChangedHandler = zkChildNodeChangedHandler;
    }

    /**
     * 1.创建节点
     * 2.添加自机器
     * 3.添加监听器
     */
    public void initZkNode() {
        try {
            logger.info("init zk config and add listener.");
            //连接状态的监听器
            curatorFramework.getConnectionStateListenable().addListener(new ConnectionStateListener() {
                @Override
                public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                    logger.info("zookeeper connection state :" + connectionState.name());
                }
            });
            //创建目录(持久节点)
            Stat nodeStat = curatorFramework.checkExists().forPath(ZkNode.NODE_PATH);
            if (null == nodeStat) {
                curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).
                        withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(ZkNode.NODE_PATH, ZkNode.NODE_PATH.getBytes());
                logger.info("zookeeper create server node path:" + ZkNode.NODE_PATH);
            }
            Stat dataStat = curatorFramework.checkExists().forPath(ZkNode.PLUGIN_PATH);
            if (null == dataStat) {
                curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).
                        withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(ZkNode.PLUGIN_PATH, ZkNode.PLUGIN_PATH.getBytes());
                logger.info("zookeeper create plugin node path:" + ZkNode.PLUGIN_PATH);
            }
            //创建数据节点
            createServerNode();
            //创建节点监听器(集群节点管理)
            addListener(ZkNode.NODE_PATH);
            addListener(ZkNode.PLUGIN_PATH);
            logger.info("Zk Started.");
        } catch (Exception e) {
            logger.error("Zk Init Manager Err", e);
        }
    }

    private void addListener(final String path) throws Exception {
        logger.info("zookeeper add listener(watcher) ,path:" + path);
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, path, true);
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                zkChildNodeChangedHandler.handler(curatorFramework, pathChildrenCacheEvent, path);
            }
        });
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
    }

    private void createServerNode() throws Exception {
        //注册机器，临时节点
        String ip = "";
        int idx = 1;
        while (null != curatorFramework.checkExists().forPath(ZkNode.SERVER_DATA + idx)) {
            String data = new String(curatorFramework.getData().forPath(ZkNode.SERVER_DATA + idx));
            if (ip.equals(data)) {
                try {
                    //有可能zk已经剔除去了
                    curatorFramework.delete().forPath(ZkNode.SERVER_DATA + idx);
                } catch (Exception e) {
                    logger.error("create zk server node err.", e);
                }
                break;
            }
            idx++;
        }
        //创建节点管理，同时支持分布式锁  path:server_idx  value:ip ???可能会有一点问题：创建该节点时，又已经有了,尽管前面做了处理
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(ZkNode.SERVER_DATA + idx, ip.getBytes());
        logger.info("create server childNode  path:" + ZkNode.SERVER_DATA + idx);
    }

    public void changedNodeData(String nodeData, String operatorType) throws Exception {
        curatorFramework.setData().forPath(operatorType, nodeData.getBytes());
        logger.info("Zookeeper updateNodeData node:{},data:{}", operatorType, nodeData);
    }


}
