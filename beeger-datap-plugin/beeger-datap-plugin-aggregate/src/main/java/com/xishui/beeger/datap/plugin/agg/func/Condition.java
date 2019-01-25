package com.xishui.beeger.datap.plugin.agg.func;

public interface Condition<T> {

     boolean condition(T t);

     String conditionKey();
}
