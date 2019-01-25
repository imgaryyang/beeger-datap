package com.xishui.beeger.datap.engine.adaptor.mapper;

public interface EngineMapper<E,T> {

    T mapper(E result);
}
