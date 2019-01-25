package com.xishui.beeger.datap.engine.kylin;

import com.xishui.beeger.datap.engine.adaptor.connection.EngineConnection;
import com.xishui.beeger.datap.engine.adaptor.execution.GenericEngineExecution;
import com.xishui.beeger.datap.engine.adaptor.execution.jdbc.DefaultJdbcEngineExecution;
import com.xishui.beeger.datap.engine.adaptor.mapper.EngineMapper;
import com.xishui.beeger.datap.engine.adaptor.processor.GenericDataProcessorAdaptor;
import com.xishui.beeger.datap.model.compute.EngineParamKeys;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class KylinDataProcessorAdaptor extends GenericDataProcessorAdaptor<List<Map<String, Object>>, Connection, ResultSet> {

    @Override
    public EngineConnection<Connection> builderConnection() {
        return new KylinEngineConnection();
    }

    @Override
    public GenericEngineExecution<List<Map<String, Object>>, Connection, ResultSet> builderExecution() {
        return new DefaultJdbcEngineExecution();
    }

    @Override
    public EngineMapper<ResultSet, List<Map<String, Object>>> builderMapper() {
        return new KylinEngineMapper();
    }

    /**
     * kylin的参数检查
     */
    @Override
    public void check(Map<String, Object> param, Map<String, String> extendParams) throws Exception {
        //检查请求参数
        if (!param.containsKey(EngineParamKeys.KylinParam.KYLIN_CONNECTION_URL)) {
            throw new RuntimeException("Kylin Engine Param connect URL is null.");
        }
        if (!param.containsKey(EngineParamKeys.KylinParam.KYLIN_CONNECTION_USERNAME)) {
            throw new RuntimeException("Kylin Engine Param connect USERNAME is null.");
        }
        if (!param.containsKey(EngineParamKeys.KylinParam.KYLIN_CONNECTION_PASSWORD)) {
            throw new RuntimeException("Kylin Engine Param connect PASSWORD is null.");
        }
    }
}
