package com.xishui.beeger.datap.engine.adaptor.execution;

import com.xishui.beeger.datap.engine.adaptor.mapper.EngineMapper;

import java.util.Map;

public interface EngineExecution<T,C,E> {

    T execution(C connection, Map<String,Object> params, EngineMapper<E,T> engineMapper);
}
