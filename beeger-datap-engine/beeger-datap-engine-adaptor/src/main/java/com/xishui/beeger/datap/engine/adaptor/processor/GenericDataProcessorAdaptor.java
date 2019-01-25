package com.xishui.beeger.datap.engine.adaptor.processor;

import com.xishui.beeger.datap.common.spi.Checker;
import com.xishui.beeger.datap.engine.adaptor.connection.EngineConnection;
import com.xishui.beeger.datap.engine.adaptor.execution.GenericEngineExecution;
import com.xishui.beeger.datap.engine.adaptor.mapper.EngineMapper;
import com.xishui.beeger.datap.model.Result;

import java.util.Map;

/**
 * @param <T> 最终返回值类型 例如List<String>
 * @param <C> 链接类型 例如java.sql.Connection
 * @param <E> db执行返回结果类型 java.sql.ResultSet
 */
public abstract class GenericDataProcessorAdaptor<T, C, E> implements DataProcessorAdaptor<T>, Checker<Map<String, Object>> {

    @Override
    public Result<T> dataProcessor(Map<String, Object> params) throws Exception {
        EngineConnection<C> engineConnection = null;
        C connection = null;
        try {
            check(params, null);
            engineConnection = builderConnection();
            GenericEngineExecution<T, C, E> engineExecution = builderExecution();
            EngineMapper<E, T> engineMapper = builderMapper();
            connection = engineConnection.createConnection(params);
            T executionResult = engineExecution.execution(connection, params, engineMapper);
            return Result.ofSuccess(executionResult);
        } catch (Exception e) {
            return Result.ofFail("DataProcessor Err.", e.getMessage());
        } finally {
            if (null != engineConnection && null != connection) {
                engineConnection.releaseConnection(connection);
            }
        }

    }

    public abstract EngineConnection<C> builderConnection();

    public abstract GenericEngineExecution<T, C, E> builderExecution();

    public abstract EngineMapper<E, T> builderMapper();
}
