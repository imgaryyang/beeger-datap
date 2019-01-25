package com.xishui.beeger.datap.currency.zk.handler;

import com.xishui.beeger.datap.currency.zk.ZkChangedHandler;
import com.xishui.beeger.datap.currency.zk.ZkEventType;
import com.xishui.beeger.datap.currency.zk.ZkNode;
import com.xishui.beeger.datap.currency.zk.ZkNodeData;
import com.xishui.beeger.datap.currency.zk.holder.ZkEventHandlerHolder;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 */
public class ZkChildNodeChangedHandler implements ZkChangedHandler<PathChildrenCacheEvent> {
    private final Logger logger = LoggerFactory.getLogger(ZkChildNodeChangedHandler.class);

    @Override
    public void handler(CuratorFramework curatorFramework, PathChildrenCacheEvent changedEvent, String parentPath) {
        try {
            logger.info("Zookeeper Watcher Callback :" + changedEvent.toString());
            if (ZkNode.PLUGIN_PATH.equals(parentPath)) {
                //子节点的新增和删除触发
                if (changedEvent.getType().name().equals(PathChildrenCacheEvent.Type.CHILD_ADDED.name())) {
                    String dataPath = changedEvent.getData().getPath();
                    byte[] datas = changedEvent.getData().getData();
                    logger.info("Zk Added Node:" + changedEvent.getData().getPath());
                    ZkEventHandlerHolder.HANDLER_HOLDER.getEventHandler(ZkEventType.ADD_COMPUTE_EVENT)
                            .eventHandler(curatorFramework, new ZkNodeData(dataPath, new String(datas)));
                }
                if (changedEvent.getType().name().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED.name())) {
                    logger.info("Zk Remove Node:" + changedEvent.getData().getPath());
                }
            } else {
                logger.info("zookeeper watcher callback other.");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


}
