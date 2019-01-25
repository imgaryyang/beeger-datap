package com.xishui.beeger.datap.currency.zk.holder;

import com.xishui.beeger.datap.currency.zk.ZkEventType;
import com.xishui.beeger.datap.currency.zk.handler.DefaultZkEventHandler;
import com.xishui.beeger.datap.currency.zk.handler.ZkEventHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ZkEventHandlerHolder {
    HANDLER_HOLDER;
    private static final Map<ZkEventType, ZkEventHandler> HANDLERS = new ConcurrentHashMap<>();

    public void registerEventHandler(ZkEventType zkEventType, ZkEventHandler zkEventHandler) {
        ZkEventHandlerHolder.HANDLERS.putIfAbsent(zkEventType, zkEventHandler);
    }

    public ZkEventHandler getEventHandler(ZkEventType zkEventType) {
        return ZkEventHandlerHolder.HANDLERS.getOrDefault(zkEventType, new DefaultZkEventHandler());
    }
}
