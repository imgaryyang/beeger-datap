package com.xishui.beeger.datap.plugin.agg.func;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Group<M, T> {
    public static <M, T> Group<M, T> newGroup() {
        return new Group<M, T>();
    }

    public final Map<T, List<M>> groupBy(List<M> values, GroupKey<M, T> groupKey) {
        if (null == values || values.size() <= 0) {
            return null;
        }
        if (null == groupKey) {
            return null;
        }
        return values.stream().collect(Collectors.groupingBy(val -> groupKey.groupKey(val)));
    }

    public interface GroupKey<M, T> {
        T groupKey(M m);
    }

}
