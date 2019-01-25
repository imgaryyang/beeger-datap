package com.xishui.beeger.datap.plugin.api.query.mysql;

import com.xishui.beeger.datap.plugin.api.query.AutoMappers;
import com.xishui.beeger.datap.plugin.api.query.DataMapper;

import java.util.Map;

public class DefaultDataMapper<T> implements DataMapper<T> {
    private Class<T> clazz;

    public DefaultDataMapper(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T mapper(Map values) {
        return AutoMappers.newAuto().mapper(values, clazz);
    }
}
