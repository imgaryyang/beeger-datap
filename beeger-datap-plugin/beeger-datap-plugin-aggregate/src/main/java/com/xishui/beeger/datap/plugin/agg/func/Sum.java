package com.xishui.beeger.datap.plugin.agg.func;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sum<T> {

    public static <T> Sum<T> newSum() {
        return new Sum();
    }

    public Map<String, BigDecimal> sums(Map<String, List<T>> values, SumValue<T> sumValue) {
        if (null == values || values.isEmpty() || null == sumValue) {
            return null;
        }
        final Map<String, BigDecimal> result = new HashMap<>();
        for (Map.Entry<String, List<T>> entry : values.entrySet()) {
            BigDecimal bigDecimal = new BigDecimal(0);
            for (T t : entry.getValue()) {
                bigDecimal = bigDecimal.add(sumValue.valueOf(t));
            }
            result.putIfAbsent(entry.getKey(), bigDecimal);
        }
        return result;
    }

    public BigDecimal sum(List<T> values, SumValue<T> sumValue) {
        if (null == values || values.size() <= 0 || null == sumValue) {
            return null;
        }
        BigDecimal bigDecimal = new BigDecimal(0);
        for (T t : values) {
            bigDecimal = bigDecimal.add(sumValue.valueOf(t));
        }
        return bigDecimal;
    }

    public BigDecimal sum(Map<String, T> values, SumValue<T> sumValue) {
        if (null == values || values.isEmpty() || null == sumValue) {
            return null;
        }
        BigDecimal bigDecimal = new BigDecimal(0);
        for (Map.Entry<String, T> entry : values.entrySet()) {
            bigDecimal = bigDecimal.add(sumValue.valueOf(entry.getValue()));
        }
        return bigDecimal;
    }

    public interface SumValue<T> {
        BigDecimal valueOf(T t);
    }
}
