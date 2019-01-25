package com.xishui.beeger.datap.web.config;

import com.xishui.beeger.datap.currency.zk.ZkEventType;
import com.xishui.beeger.datap.currency.zk.ZkProperties;
import com.xishui.beeger.datap.currency.zk.ZkStarted;
import com.xishui.beeger.datap.currency.zk.holder.ZkEventHandlerHolder;
import com.xishui.beeger.datap.web.zk.ZkAddedPluginEventHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ZkConfigStarter {
    @Value(value = "${zk.url}")
    private String zkUrl;

    @PostConstruct
    public void initZk() {
        try {
            ZkProperties zkProperties = new ZkProperties();
            zkProperties.setConnect(zkUrl);
            ZkStarted.ZK.started(zkProperties);
            ZkEventHandlerHolder.HANDLER_HOLDER
                    .registerEventHandler(ZkEventType.ADD_COMPUTE_EVENT, new ZkAddedPluginEventHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
