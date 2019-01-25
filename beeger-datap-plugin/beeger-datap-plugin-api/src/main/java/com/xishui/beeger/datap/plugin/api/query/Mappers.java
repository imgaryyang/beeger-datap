package com.xishui.beeger.datap.plugin.api.query;

import java.util.Date;
import java.util.Map;

public class Mappers {
    private Map map;

    public Mappers() {

    }

    public Mappers(Map map) {
        this.map = map;
    }

    public static Mappers newMapper(Map map) {
        return new Mappers(map);
    }

    public String stringValue(String key) {
        return map.containsKey(key) ? String.valueOf(map.get(key)) : null;
    }

    public Integer intValue(String key) {
        String value = stringValue(key);
        return null == value ? null : Integer.valueOf(value);
    }

    public Double doubleValue(String key) {
        String value = stringValue(key);
        return null == value ? null : Double.valueOf(value);
    }

    public Date dateValue(String key) {
        String value = stringValue(key);
        return null == value ? null : new Date(Long.valueOf(value));
    }

    public Float floatValue(String key) {
        String value = stringValue(key);
        return null == value ? null : Float.valueOf(value);
    }
}
