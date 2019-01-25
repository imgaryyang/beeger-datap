package com.xishui.beeger.datap.plugin.api.connection;

import java.util.HashMap;
import java.util.Map;

public enum ConnectionHolder {
    HOLDER;
    private final static Map<String, GenericConnection> CONNECTIONS = new HashMap<>();

    public void cacheConnection(String connectionKey, GenericConnection genericConnection) {
        ConnectionHolder.CONNECTIONS.putIfAbsent(connectionKey, genericConnection);
    }

    public GenericConnection getConnection(String connectionKey) {
        return ConnectionHolder.CONNECTIONS.getOrDefault(connectionKey, null);
    }
}
