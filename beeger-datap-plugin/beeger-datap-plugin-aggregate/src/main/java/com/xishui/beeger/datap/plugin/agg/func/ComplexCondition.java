package com.xishui.beeger.datap.plugin.agg.func;

import java.util.List;

public interface ComplexCondition<T> {

    List<Condition<T>> conditions();
}
