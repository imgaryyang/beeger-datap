package com.xishui.beeger.datap.plugin.api.holder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum  ComputePluginInvokeHolder {
    HOLDER;
    private static Map<String,Class<?>> INVOKES = new ConcurrentHashMap<>();

    public void putInvoke(String invokeKey,Class<?> invokeClazz){
        ComputePluginInvokeHolder.INVOKES.putIfAbsent(invokeKey,invokeClazz);
    }

    public Class<?> getInvoke(String invokeKey){
        return ComputePluginInvokeHolder.INVOKES.getOrDefault(invokeKey,null);
    }
}
