package com.xishui.beeger.datap.plugin.agg.func;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Counter<T> {

    public static <T>Counter<T> newCounter() {
        return new Counter();
    }

    public Integer counter(List<T> values, Condition<T> condition) {
        if (null == values) {
            return -1;
        }
        List<T> filterValues = values.stream().filter(new Predicate<T>() {
            @Override
            public boolean test(T t) {
                return null == condition ? false : condition.condition(t);
            }
        }).collect(Collectors.toList());
        return counter(filterValues);
    }

    public Map<String, Integer> counters(List<T> values, ComplexCondition<T> complexCondition) {
        if (null == complexCondition) {
            return new HashMap<>();
        }
        final List<Condition<T>> conditions = complexCondition.conditions();
        if (null == conditions || conditions.size() <= 0) {
            return new HashMap<>();
        }
        final Map<String, Integer> counters = new HashMap<>();
        conditions.forEach(condition -> {
            counters.putIfAbsent(condition.conditionKey(), counter(values, condition));
        });
        return counters;
    }

    public Integer counter(List<T> values) {

        return null == values ? -1 : values.size();
    }
}
