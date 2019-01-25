package com.xishui.beeger.datap.router.spi;

import com.xishui.beeger.datap.model.compute.EngineModel;

import java.util.HashMap;
import java.util.Map;

public class RouterModel {
    /**
     * 路由类型
     */
    private EngineModel engineModel;
    /**
     * 所有的参数
     */
    private Map<String, Object> params = new HashMap<>();

    public static RouterModel create() {
        return new RouterModel();
    }

    public RouterModel model(EngineModel model) {
        this.engineModel = model;
        return this;
    }

    public RouterModel attribute(String key, Object value) {
        this.params.put(key, value);
        return this;
    }

    public RouterModel attributeMap(Map<String, Object> paramMap) {
        this.params.putAll(paramMap);
        return this;
    }

    public boolean containKey(String key) {
        return params.containsKey(key);
    }

    public String valueOfString(String key) {
        return params.containsKey(key) ? (String) params.get(key) : null;
    }

    public Map valueOfMap(String key) {
        return params.containsKey(key) ? (Map) params.get(key) : null;
    }

    public EngineModel getEngineModel() {
        return engineModel;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}
