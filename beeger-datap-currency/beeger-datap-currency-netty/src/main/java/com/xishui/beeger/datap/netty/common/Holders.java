package com.xishui.beeger.datap.netty.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Holders {

    private static final Map<String, String> HODLER = new ConcurrentHashMap<>();

    public static void putHolderValue(String key, String value) {
        HODLER.put(key, value);
    }

    public static String pullHolderValue(String key) {
        return HODLER.containsKey(key) ? HODLER.get(key) : null;
    }

    public static void removeHolder(String key) {
        if (HODLER.containsKey(key)) {
            HODLER.remove(key);
        }
    }

    public final class Keys {
        public static final String SERVER_URL = "server.url";
    }
}
