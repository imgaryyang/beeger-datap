package com.xishui.beeger.datap.container;

import java.util.HashMap;
import java.util.Map;

public class ContainerConfig {

    private Map<String, Object> config = new HashMap<>();

    public static ContainerConfig config() {
        return new ContainerConfig();
    }

    public ContainerConfig addAttribute(String key, Object value) {
        config.put(key, value);
        return this;
    }

    public String attributeString(String key) {
        if (config.containsKey(key)) {
            Object value = config.get(key);
            if (value instanceof String) {
                return (String) value;
            }
        }
        return null;
    }

    public Integer attributeInteger(String key) {
        if (config.containsKey(key)) {
            Object value = config.get(key);
            if (value instanceof Integer) {
                return (Integer) value;
            }
        }
        return null;
    }
}
