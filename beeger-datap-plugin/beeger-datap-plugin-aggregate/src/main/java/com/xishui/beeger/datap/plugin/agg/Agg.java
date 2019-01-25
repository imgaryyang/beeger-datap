package com.xishui.beeger.datap.plugin.agg;

import com.xishui.beeger.datap.plugin.agg.func.Counter;
import com.xishui.beeger.datap.plugin.agg.func.Filter;
import com.xishui.beeger.datap.plugin.agg.func.Group;
import com.xishui.beeger.datap.plugin.agg.func.Sum;

public class Agg {

    public static <T> Counter<T> counter() {
        return Counter.newCounter();
    }

    public static <M, T> Group<M, T> group() {
        return Group.newGroup();
    }

    public static Filter filter() {
        return Filter.newFilter();
    }

    public static <T> Sum<T> sum() {
        return Sum.newSum();
    }
}
