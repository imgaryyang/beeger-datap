package com.xishui.beeger.datap.plugin.agg.convert;

import java.util.List;
import java.util.Map;

public class MapToList<T, M> {

    public static <T, M> MapToList<T, M> newMapToList() {
        return new MapToList();
    }

    public List<T> mapToList(Map<String, M> values, final List<T> resumeValue, Mappered<T, M> mappered) {
        if (null == values || values.isEmpty() || null == mappered || null == resumeValue) {

        }
        values.entrySet().forEach(entry -> {
            mappered.mappered(entry, resumeValue);
        });
        return resumeValue;
    }

    public interface Mappered<T, M> {
        void mappered(Map.Entry<String, M> entry, List<T> resumeValue);
    }

}
