package com.xishui.beeger.datap.engine.adaptor.execution;

import com.xishui.beeger.datap.engine.adaptor.mapper.EngineMapper;

import java.util.Map;

/**
 *  通用模型，与链接模型无关
 * @param <T>
 * @param <C>
 * @param <E>
 */
public abstract class GenericEngineExecution<T, C, E> implements EngineExecution<T, C, E> {
    @Override
    public T execution(C connection, Map<String, Object> params, EngineMapper<E, T> engineMapper) {
        if (null == engineMapper) {
            throw new RuntimeException("GenericEngineExecution.EngineMapper is null.");
        }
        E resultSet = doExecution(connection, params);
        if (null != resultSet) {
            return engineMapper.mapper(resultSet);
        }
        return null;
    }

    public abstract E doExecution(C connection, Map<String, Object> params);

}
